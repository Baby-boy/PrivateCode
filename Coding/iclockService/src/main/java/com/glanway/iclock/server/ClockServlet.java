package com.glanway.iclock.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.glanway.iclock.common.CommandWrapper;
import com.glanway.iclock.entity.employee.Employee;
import com.glanway.iclock.entity.employee.EmployeeDeviceInfo;
import com.glanway.iclock.entity.employee.FingerFaceTemplate;
import com.glanway.iclock.entity.sign.Device;
import com.glanway.iclock.entity.sign.Sign;
import com.glanway.iclock.entity.task.Task;
import com.glanway.iclock.entity.vo.device.EmployeeDeviceFingerFaceVo;
import com.glanway.iclock.entity.vo.device.EmployeeDeviceInfoVO;
import com.glanway.iclock.service.employee.EmployeeDeviceInfoService;
import com.glanway.iclock.service.employee.EmployeeService;
import com.glanway.iclock.service.employee.FingerFaceTemplateService;
import com.glanway.iclock.service.sign.DeviceService;
import com.glanway.iclock.service.sign.SignService;
import com.glanway.iclock.service.task.TaskService;
import com.glanway.iclock.util.DateUtils;
import com.glanway.iclock.util.StringUtil;
import com.glanway.iclock.util.TimeUtil;

/**
 * 中控考勤机 push-sdk 服务器接口.
 * <p>
 * TODO 增加掉线检测. TODO 如果是新设备则将服务器用户信息全部更新到机器,否则定期更新. TODO 通知新信息时
 * <p>
 * 设备接入数据默认处理.
 * <p>
 * 1. 在线设备. 1.1 新增: 直接服务器新增并同步到其他设备. 1.2 更新: 直接服务器更新并同步到其他设备. 1.3 删除:
 * 直接服务器删除并同步到其他设备.
 * <p>
 * 2. 新设备接入: 第一次接入处理流程: 1. 第一次接入清空数据 2. 从服务器同步所有用户数据.
 * <p>
 * 3. 历史设备断线重连: 断线更新问题: 更新后未来得及上报/上报失败/没有网络更新, 其他设备也可能更新,
 * 但是设备上报时没有新增/更新时间,因此无法获知到底应该以哪一台设备为准. 3.1 断线新增: 直接服务器新增(不会对服务器产生什么影响). 3.2
 * 断线更新: 认为是无效更新, 不处理更新 (以服务器为准, 否则历史设备N久前的数据会覆盖当前数据). 3.2 断线删除: 认为是无效删除,
 * 以服务器为准. 因此旧设备接入处理流程: 1. 使用服务器信息覆盖设备信息. 2.
 * 同步客户端信息到服务器(此时新增外其他均与服务器一致,因此不需要在考虑断线更新和断线删除问题)
 *
 * @author yangchanghe
 * @version 1.0
 * @since 1.0
 */
@Component("iclock")
public class ClockServlet extends HttpServlet {
	
	private static final long serialVersionUID = -2036458039532875830L;

	private static final Logger LOGGER = LoggerFactory.getLogger(ClockServlet.class);

	private static final String UNSET = "0";

	/**
	 * 3.2.2 考勤记录表名.
	 */
	private static final String TAB_ATTLOG = "ATTLOG";

	/**
	 * 3.2.2 操作日志表名.
	 */
	private static final String TAB_OPERLOG = "OPERLOG";

	/**
	 * 3.2.2 考勤照片表名.
	 */
	private static final String TAB_ATTPHOTO = "ATTPHOTO";

	/**
	 * 3.2.2 短信息.
	 */
	private static final String TAB_SMS = "SMS";

	/**
	 * 3.2.2 个人短信息.
	 */
	private static final String TAB_USER_SMS = "USER_SMS";

	/**
	 * 3.2.2 用户信息.
	 */
	private static final String TAB_USERINFO = "USERINFO";

	/**
	 * 3.2.2 指纹模板表名.
	 */
	private static final String TAB_FINGERTMP = "FINGERTMP";

	/**
	 * 3.2.2 人脸模板表名.
	 */
	private static final String TAB_FACE = "FACE";

	/**
	 * 3.2.2 用户照片表名.
	 */
	private static final String TAB_USERPIC = "USERPIC";

	/**
	 * 语言: 中文.
	 */
	private static final String LANG_CN = "83";

	/**
	 * 语言: 英文.
	 */
	private static final String LANG_EN = "69";

	/**
	 * 3.2.4-1 执行系统命令.
	 * <p>
	 * 返回格式:<br>
	 * ID=11<br>
	 * SN=xx<br>
	 * Return=XX<br>
	 * CMD=Shell<br>
	 * FILENAME=shellout.txt<br>
	 * Content=SSS
	 * </p>
	 */
	private static final String CMD_SHELL = "C:%s:SHELL %s";

	/**
	 * 3.2.4-2 检查数据更新.
	 * <p>
	 * 要求设备从服务器读取设备的配置信息,然后检查设备内数据的更新情况,若有新的数据(考勤/用户信息)，立即传送到服务器.<br>
	 * POST格式: ID=?&SN=?&Return=?&CMD=CHECK
	 * </p>
	 */
	private static final String CMD_CHECK = "C:%s:CHECK";

	/**
	 * 3.2.4-3 清除数据 - 清除考勤记录.
	 * <p>
	 * POST格式:ID=?&Return=?
	 * </p>
	 */
	private static final String CMD_CLEAR_LOG = "C:%s:CLEAR LOG";

	/**
	 * 3.2.4-3 清除数据 - 清除所有用户信息.
	 */
	private static final String CMD_CLEAR_ALL_USERINFO = "C:%s:CLEAR ALL USERINFO";

	/**
	 * 3.2.4-3 清除数据 - 清除所有数据.
	 * <p>
	 * POST格式:ID=?&Return=?
	 * </p>
	 */
	private static final String CMD_CLEAR_DATA = "C:%s:CLEAR DATA";

	/**
	 * 3.2.4-3 清除数据 - 清除现场照片数据.
	 * <p>
	 * POST格式:ID=?&Return=?
	 * </p>
	 */
	private static final String CMD_CLEAR_PHOTO = "C:%s:CLEAR PHOTO";

	/**
	 * 3.2.4-4 发送设备信息到服务器.
	 * <p>
	 * POST格式:<br>
	 * ID=?&Return=?&CMD=INFO<br>
	 * OPTIONS(每行为Item=Value)
	 * </p>
	 */
	private static final String CMD_INFO = "C:%s:INFO";

	/**
	 * 3.2.4-5 设置设备选项.
	 */
	private static final String CMD_SET_OPTION = "C:%s:SET OPTION %s=%s";

	/**
	 * 3.2.4-6 重启设备.
	 */
	private static final String CMD_REBOOT = "C:%s:REBOOT";

	/**
	 * 3.2.4-7 数据命令.
	 * <p>
	 * 数据命令格式: DATA {subcmd}<br>
	 * subcmd格式: UPDATE|DELETE|QUERY tablename value|key
	 * </p>
	 */
	private static final String CMD_DATA = "C:%s:DATA %s";

	/**
	 * 3.2.4-8 重新加载系统选项.
	 * <p>
	 * 要求设备重新载入系统配置和选项，这样修改后的系统选项才能生效.<br>
	 * POST格式: ID=iiii&SN=xxxx&Return=0
	 * </p>
	 */
	private static final String CMD_RELOAD_OPTIONS = "C:%s:RELOAD OPTIONS";

	/**
	 * 3.2.4-9 登记用户指纹.
	 */
	private static final String CMD_ENROLL_FINGER = "C:%s:ENROLL_FP %s";

	/**
	 * 3.2.4-10 检查并传送考勤新数据.
	 * <p>
	 * 要求设备立即检查是否有新的考勤数据，并立即把新数据传送到服务器.
	 * </p>
	 */
	private static final String CMD_LOG = "C:%s:LOG";

	/**
	 * 3.2.4-11 打开门锁信号.
	 */
	private static final String CMD_UNLOCK = "C:%s:AC_UNLOCK";

	/**
	 * 3.2.5.3 考勤对账. Start=yyyy-MM-hh[ HH:mm:ss] 如果没有传递HH:mm:ss则为 00:00:00
	 * End=yyyy-MM-hh[ HH:mm:ss] 如果没有传递HH:mm:ss则为 23:59:59 Count: 对账记录数,
	 * 如果实际考勤记录没有达到Count指定值, 则重新传递该时间内所有考勤记录.
	 */
	private static final String CMD_ACCOUNT = "C%s:ACCOUNT Start=%s End=%s Count=%s";

	/**
	 * 3.2.5.4 设备操作数据重传.
	 */
	private static final String CMD_SYNC = "C:%s:SYNC Start=%s End=%s";

	/**
	 * 3.2.4-12 取消报警信号.
	 */
	private static final String CMD_UNALARM = "C:%s:AC_UNALARM";

	private static final String CMD_RELOAD_DATA = "C:%s:RELOAD DATA";

	private final String CMD_DATA_QUERY_ATTLOG = "C:%s:DATA QUERY ATTLOG ";
	private final String CMD_DATA_QUERY_USER_ALL = "C:%s:DATA QUERY USERINFO";
	private final String CMD_DATA_QUERY_FINGER_ALL = "C:%s:DATA QUERY FINGERTMP";
	private final String CMD_DATA_QUERY_PHOTO_ALL = "C:%s:DATA QUERY USERPIC";
	private final String CMD_DATA_QUERY_FACE_ALL = "C:%s:DATA QUERY FACE";

	/**
	 * 更新用户信息 C:%s:DATA UPDATE USERINFO %s
	 */
	private final String CMD_DATA_UPDATE_USER = "C:%s:DATA UPDATE USERINFO %s";

	private final String CMD_DATA_UPDATE_FINGER = "C:%s:DATA UPDATE FINGERTMP %s";
	private final String CMD_DATA_UPDATE_PHOTO = "C:%s:DATA UPDATE USERPIC %s";
	private final String CMD_DATA_UPDATE_FACE = "C:%s:DATA UPDATE FACE %s";

	/**
	 * 删除用户信息指令 C:%s:DATA DELETE USERINFO %s
	 */
	private final String CMD_DATA_DELETE_USER = "C:%s:DATA DELETE USERINFO %s";
	private final String CMD_DATA_DELETE_FINGER = "C:%s:DATA DELETE FINGERTMP %s";
	private final String CMD_DATA_DELETE_PHOTO = "C:%s:DATA DELETE USERPIC %s";
	private final String CMD_DATA_DELETE_FACE = "C:%s:DATA DELETE FACE %s";

	/**
	 * PUSH API 接口命名空间.
	 */
	private static final String PUSH_NS_URI = "/iclock";

	/**
	 * PUSH API 配置查看地址.
	 */
	private static final String PUSH_CFG_URI = "/iclock/cfg";

	/**
	 * 设备获取初始化配置URI.
	 */
	private static final String PUSH_INIT_URI = "/iclock/cdata";

	/**
	 * 设备获取指令URI.
	 */
	private static final String PULL_COMMANDS_URI = "/iclock/getrequest";

	/**
	 * 设备推送数据URI.
	 */
	private static final String PUSH_DATA_URI = "/iclock/cdata";

	/**
	 * 设备推送指令执行结果URI.
	 */
	private static final String PUSH_COMMAND_RETURN_URI = "/iclock/devicecmd";

	/**
	 * UTF-8 encoding.
	 */
	private static final Charset GB2312 = Charset.forName("GB2312");
	/**
	 * UTF-8 encoding.
	 */
	private static final Charset UTF_8 = Charset.forName("UTF-8");

	/**
	 * 配置文件位置参数.
	 */
	private static final String CFG_LOCATION_PARAM = "cfg-location";
	private static final String CLASSPATH_PREFIX = "classpath:";
	private static final String DEFAULT_CFG_LOCATION = "iclock.cfg.properties";

	/**
	 * 一分钟的毫秒数.
	 */
	private static final long MINUTE_MILLIS = 60 * 1000;

	private static final String DEV_INIT_OVER_ID_PREFIX = "INIT_OVER-";
	private static final String DEV_DIRTY_CHECK_ID_PREFIX = "DIRTY_CHECK-";
	private static final String DEV_DIRTY_CHECK_USER_ID_PREFIX = "DIRTY_CHECK_U-";
	private static final String DEV_DIRTY_CHECK_FINGER_ID_PREFIX = "DIRTY_CHECK_FP-";
	private static final String DEV_DIRTY_CHECK_PHOTO_ID_PREFIX = "DIRTY_CHECK_PT-";
	private static final String DEV_DIRTY_CHECK_FACE_ID_PREFIX = "DIRTY_CHECK_FE-";
	private static final String DEV_INIT_CLEAR_LOG_ID_PREFIX = "INIT_CAU-";
	private static final String DEV_INIT_CLEAR_PHOTO_ID_PREFIX = "INIT_CAU-";
	private static final String DEV_INIT_CLEAR_ALL_USER_ID_PREFIX = "INIT_CAU-";
	private static final String DEV_RELOAD_DATA_ID_PREFIX = "INIT_RD-";
	private static final String DEV_RELOAD_OPTIONS_ID_PREFIX = "INIT_RO-";

	private Charset charset = GB2312;

	/**
	 * 脏检查间隔, 单位分钟.
	 * <p>
	 * 用于检查设备删除对象, 如果该值 < 1 则表示禁用, 不检查设备删除对象.
	 * </p>
	 */
	private int dirtyCheckPeriod = 1; // minutes

	/**
	 * 新设备接入时, 是否清空新设备数据.
	 */
	private boolean clearNewDevice = false;

	/**
	 * 已接入过的设备, 是否支持离线更新.
	 */
	private boolean offlineUpdate = true;

	/**
	 * iclock 配置.
	 */
	private Properties props;

	private Map<String, Boolean> devices = new LinkedHashMap<>();

	/**
	 * 设备命令队列.
	 */
	private final Map<String, List<String>> commandQueue = new LinkedHashMap<>();

	/**
	 * 设备及表最后访问时间.
	 */
	private final Map<String/* sn-table */, String> devicesTableLastStamp = new LinkedHashMap<>();

	private final Map<String, Long> devicesLastDirtyTimestamp = new ConcurrentHashMap<>();

	/**
	 * 设备数据服务器端缓存.
	 */
	private final Map<String/* sn-table */, Map<String, String>> deviceDataCache = new LinkedHashMap<>();

	/**
	 * 任务接口注入
	 */
	@Autowired
	private TaskService taskService;
	/**
	 * 员工接口注入
	 */
	@Autowired
	private EmployeeService employeeService;
	/**
	 * 员工的考勤信息接口注入
	 */
	@Autowired
	private EmployeeDeviceInfoService employeeDeviceInfoService;

	/**
	 * 员工的指纹模板和脸纹模板service注入
	 */
	@Autowired
	private FingerFaceTemplateService fingerFaceTemplateService;

	/**
	 * 员工考勤记录service注入
	 */
	@Autowired
	private SignService signService;

	/**
	 * 设备service注入
	 */
	@Autowired
	private DeviceService deviceService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init() throws ServletException {
		final String location = getInitParameter(CFG_LOCATION_PARAM);
		final Properties defaults = load(ClockServlet.class, DEFAULT_CFG_LOCATION, null);

		if (null == defaults) {
			LOGGER.error("default iclock config file '{}' can't load", DEFAULT_CFG_LOCATION);
			throw new ServletException("iclock config file not found, can't initialize iclock servlet");
		}

		Properties props = null;
		if (null != location) {
			props = load(getClass(), location, defaults);
		}
		if (null == props) {
			LOGGER.info("no iclock config file configure, using default configuration");
			props = new Properties(defaults);
		}
		this.props = props;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
			throws ServletException, IOException {
		final String requestUri = req.getRequestURI();
		final String ua = req.getHeader("User-Agent");
		final String sn = req.getParameter("SN");

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("request '{}' from [{}]/[{}]", requestUri, ua, sn);
		}
		try {

			// 必须是 http://host[:port]/iclock/*
			if (PUSH_NS_URI.equals(requestUri)) {
				resp.getWriter().write("OK");
			} else if (PUSH_CFG_URI.equals(requestUri)) {
				handleInitPush("Status-Test", req, resp);
			} else if (PUSH_INIT_URI.equals(requestUri) && null != sn) {
				handleInitPush(sn, req, resp);
			} else if (PULL_COMMANDS_URI.equals(requestUri) && null != sn) {
				// 设备每隔 * s主动从服务器读取命令.
				handlePull(sn, req, resp);
			} else if (null == sn) {
				resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Device not found");
			} else {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			}

		} catch (final Exception e) {
			LOGGER.error("doget()报错:", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding(charset.name());

		final String requestUri = req.getRequestURI();
		final String ua = req.getHeader("User-Agent");
		final String sn = req.getParameter("SN");
		final Date now = new Date();

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("POST '{}' from [{}]/[{}]", requestUri, ua, sn);
		}

		/*-
		 * 3.2.3 设备数据上报.
		 */
		if (PUSH_DATA_URI.equals(requestUri)) {
			final String table = req.getParameter("table");
			final String stamp = req.getParameter("Stamp"); // 本次数据的时间戳,
															// 用于初始化配置时
															// tableStamp,
															// 过时,始终是9999

			String line;
			InputStreamReader inputStreamReader = new InputStreamReader(req.getInputStream(), charset.name());
			// final BufferedReader reader = req.getReader(); // TODO encoding.
			final BufferedReader reader = new BufferedReader(inputStreamReader); // TODO
																					// encoding.
			if (TAB_ATTLOG.equals(table)) {
				/*-
				 * 3.2.3.1 上传考勤记录.
				 */
				while (null != (line = reader.readLine())) {
					handleClockItem(sn, now, line);
				}
			} else if (TAB_OPERLOG.equals(table)) {
				/*-
				 * 3.2.3.2 上传用户信息和系统日志. (通过配置后最大达到效果新增/更新后上传, 删除相关信息不会上传).
				 */
				while (null != (line = reader.readLine())) {
					/*
					if (!"3983163000313".equals(sn)) {
						continue;
					}
					*/
					if (line.startsWith("USER ")) {
						/*-
						 * 用户基本信息.
						 */
						handleUserItem(sn, now, line.substring(5));
					} else if (line.startsWith("FP ")) {
						/*-
						 * 用户指纹信息.
						 * FP PIN=1\tFID=2\tSize=1\tValid=1\tTMP=
						 */
						handleFingerprintItem(sn, now, line.substring(3));
					} else if (line.startsWith("USERPIC ")) {
						/*-
						 * 用户照片数据.
						 */
						handlePhotoItem(sn, now, line.substring(8));
					} else if (line.startsWith("FACE ")) { // 用户人脸模板
						/*-
						 * (更新人脸后, 会先更新用户照片, 脸纹会更新较慢,大约照片更新后1~2分钟).
						 * FACE PIN=1\tFID=2\tSIZE=1\tValid=1\tTMP=
						 */
						handleFaceItem(sn, now, line.substring(5));
					} else if (line.startsWith("OPLOG ")) {
						// 管理员操作日志.
						handleAdminstratorActionItem(sn, now, line.substring(5));
					} else {
						LOGGER.warn("Unknown OPERLOG message: {}", line);
					}
				}
				// } else if (TAB_ATTPHOTO.equals(table)) {
				/*-
				 * 3.2.3.3 上传考勤现场照片
				 * 此方法始终没有调用过, 貌似设备没有实现该功能.
				 */
				/*-
				 * PIN格式:
				 * 1. yyyyMMddHHmmss-PIN 考勤通过格式.
				 * 2. yyyyMMddHHmmss     考勤不通过格式.
				 */
			} else {
				LOGGER.warn("Unknown message table={}", table);
			}

			doResponse(resp, "OK");
		} else if (PUSH_COMMAND_RETURN_URI.equals(requestUri)) {
			String line;
			final BufferedReader reader = req.getReader(); // TODO encoding.
			while (null != (line = reader.readLine())) {
				final Map<String, String> result = tokenizeToMap(line, "&");
				final String commandId = result.get("ID");
				final String returnCode = result.get("Return");
				final String commandType = result.get("CMD");

				/*-
				 * 设备信息.
				 */
				if ("INFO".equals(commandType)) {
					final StringBuilder buff = new StringBuilder();
					while (null != (line = reader.readLine())) {
						buff.append(line).append("\n");
					}

					final Map<String, String> info = tokenizeToMap(buff.toString(), "\n");
					String dev = info.get("DeviceID");
					final String deviceId = null != dev ? dev : sn; // 设备ID.
					final String deviceName = info.get("~DeviceName"); // 设备名称.
					final String platform = info.get("~Platform"); // 设备平台.
					final String pushVersion = info.get("PushVersion"); // PUSH
																		// SDK
																		// 版本.
					final String fingerVersion = info.get("FPVersion"); // 指纹版本.
					final String faceVersion = info.get("FaceVersion"); // 脸纹版本.

					final String mac = info.get("MAC"); // MAC
					final String ip = info.get("IPAddress"); // IP

					final String transactionCount = info.get("TransactionCount"); // 当前考勤记录数.
					final String userCount = info.get("UserCount"); // 当前用户数.
					final String fingerCount = info.get("FPCount"); // 当前指纹数(一个人可能有多个).
					final String faceCount = info.get("FaceCount"); // 当前脸数(一个人只会有一个脸纹).

					LOGGER.info(
							"设备名称: {}, 设备ID: {}, 平台: {}, PUSH SDK版本: {}, 指纹算法版本: {}, 人脸算法版本: {},"
									+ " MAC: {}, IP: {}, 考勤记录数: {}, 用户数: {}, 指纹数: {}, 人脸数: {}",
							deviceName, deviceId, platform, pushVersion, fingerVersion, faceVersion, mac, ip,
							transactionCount, userCount, fingerCount, faceCount);
				} else {
					handleCommandReturned(sn, commandId, returnCode, commandType);
				}
			}
			doResponse(resp, "OK");
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	/*
	 * ***************************************************************
	 * 设备配置信息/指令获取.
	 * *************************************************************
	 */

	/**
	 * 3.2.2 设备读取服务器上的配置信息.
	 * <p>
	 * GET
	 * /iclock/cadata?SN=xxx&options=all&pushver=2.1.1&language=83&pushcommonkey=xx
	 * SN: 设备序列号 pushver: push SDK 协议版本. language: 语言 pushcommonkey
	 *
	 * @param sn
	 *            设备序列号.
	 * @param req
	 *            请求.
	 * @param resp
	 *            响应.
	 * @throws IOException
	 *             IOException.
	 */
	private void handleInitPush(final String sn, final HttpServletRequest req, final HttpServletResponse resp)
			throws IOException {
		final String pushVersion = req.getParameter("pushver"); // pushver >
																// 2.1.1 才支持.
		final String pushKey = req.getParameter("pushcommkey");
		final String language = req.getParameter("language"); // 83=中文, 69=英文

		/*-
		 * ErrorDelay: 为联网失败后重新联接服务器的间隔时间(秒)
		 * Delay: 为正常联网时联接服务器的间隔时间（秒）
		 * TransTimes: 为定时检查并传送新数据时间（HH:mm，24小时格式），多个时间用分号分开，最多支持10个时间
		 * TransInterval: 为检查并传送新数据间隔时间（分钟）
		 * Realtime: 是否实时传送新记录. 为1表示有新数据就传送到服务器，为0表示按照 TransTimes 和 TransInterval 规定的时 间传送
		 * TrasFlags: AttLog(考勤记录), OpLog(操作记录), AttPhoto(考勤照片), EnrollUser(录入新用户), ChgUser(修改用户),
		 *            EnrollFP(录入指纹), ChgFP(修改指纹), FPImag(指纹图片), FACE(人脸登记), UserPic(用户照片).
		 */
		final Properties options = new Properties(props);

		/*-
		 * 初始化数据推送起始时间.
		 */
		final String[] tableNames = { TAB_ATTLOG, TAB_OPERLOG, TAB_SMS, TAB_USER_SMS, TAB_USERINFO, TAB_FINGERTMP,
				TAB_FACE, TAB_USERPIC, TAB_ATTPHOTO };
		for (final String tableName : tableNames) {
			final String stamp = nvl(devicesTableLastStamp.get(sn + "-" + tableName));
			options.setProperty(tableName + "Stamp", stamp);
		}

		/*-
		 * 旧版协议兼容属性.
		 */
		options.setProperty("Stamp", options.getProperty(TAB_ATTLOG + "Stamp"));
		options.setProperty("OpStamp", options.getProperty(TAB_OPERLOG + "Stamp"));
		options.setProperty("PhotoStamp", options.getProperty(TAB_ATTPHOTO + "Stamp"));

		final StringBuilder buff = new StringBuilder("GET OPTION FROM:").append(sn).append("\n");
		final Enumeration<?> names = options.propertyNames();
		while (names.hasMoreElements()) {
			final String name = (String) names.nextElement();
			final String value = options.getProperty(name);
			buff.append(name).append('=').append(value).append('\n');
		}

		final String cfg = buff.toString();

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("设备 [{}], PUSH SDK [{}], 语言 [{}] 初始化推送配置: {}", sn, pushVersion, language, cfg);
		}

		doResponse(resp, cfg);
	}

	/**
	 * 请求获取给定考勤机指令.
	 *
	 * @param sn
	 *            设备序列号。
	 * @param req
	 *            请求.
	 * @param resp
	 *            响应.
	 */
	protected void handlePull(final String sn, final HttpServletRequest req, final HttpServletResponse resp)
			throws IOException {
		final String info = req.getParameter("INFO"); // 只有第一次会有。
		final String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());

		LOGGER.debug("设备 {} 发送心跳", sn);
		try {

			final boolean newDevice = touch(sn, info);

			Device device = deviceService.selectByDeviceSn(sn);
			if (null != device) {

				final Boolean deviceOnline = device.getState() == 2;

				// final Boolean deviceOnline = devices.get(sn);
				// final boolean newDevice = null == deviceOnline;
				final boolean online = Boolean.TRUE.equals(deviceOnline);
				/**
				 * TODO 如果当前设备状态为异常(state=3),就是离线状态
				 */
				final boolean offline = device.getState() == 3;
				/*-
				 * 如果是新设备或离线重连.
				 * !online
				 */
				// if (newDevice || offline) {
				if (offline) {
					/*-
					 * 以下两种情况清空数据, 并写入服务器数据.
					 * 1. 如果清空新设备, 且是新设备接入.
					 * 2. 如果不支持离线更新, 且是历史设备接入.
					 */
					if ((clearNewDevice && newDevice) || !offlineUpdate) {
						if (clearNewDevice && newDevice) {
							// 清除考勤信息和考勤现场照片.
							pushCommand(sn, CMD_CLEAR_LOG, DEV_INIT_CLEAR_LOG_ID_PREFIX + timestamp);
							pushCommand(sn, CMD_CLEAR_PHOTO, DEV_INIT_CLEAR_PHOTO_ID_PREFIX + timestamp);
						} else {
							clearCommands(sn);
						}

						// 清空新设备或不支持离线更新, 清空用户用户信息.
						pushCommand(sn, CMD_CLEAR_ALL_USERINFO, DEV_INIT_CLEAR_ALL_USER_ID_PREFIX + timestamp);
					}

					/*-
					 * 新考勤机或断线重连但不支持离线更新.
					 * 使用服务器数据覆盖考勤机数据
					 */
					if (newDevice || !offlineUpdate) {
						final int count = pushInitDataTo(sn);
						pushCommand(sn, CMD_RELOAD_DATA, DEV_RELOAD_DATA_ID_PREFIX + timestamp);
						pushCommand(sn, CMD_RELOAD_OPTIONS, DEV_RELOAD_OPTIONS_ID_PREFIX + timestamp);
						pushCommand(sn, CMD_LOG, DEV_INIT_OVER_ID_PREFIX + timestamp);
						if (0 < count) {
							pushCommand(sn, CMD_REBOOT, timestamp); // FIXME
						}
					} else {
						// 不是新设备且支持离线更新.
						pushCommand(sn, CMD_LOG, DEV_INIT_OVER_ID_PREFIX + timestamp);
					}
				} else {
					final long now = new Date().getTime();
					final Long lastDirtyTimestamp = devicesLastDirtyTimestamp.get(sn);
					final long lastTimestamp = null != lastDirtyTimestamp ? lastDirtyTimestamp : 0;

					/*-
					 * 需要脏检查, 则下发脏检查指令.
					 */
					if (0 < dirtyCheckPeriod && now - lastTimestamp > dirtyCheckPeriod * MINUTE_MILLIS) {

						LOGGER.info("给设备{}下发脏检查指令", sn);

						devicesLastDirtyTimestamp.put(sn, now);

						/*
						// pushCommand(sn, CMD_CHECK, DEV_DIRTY_CHECK_ID_PREFIX
						// +
						// timestamp);
						pushCommand(sn, CMD_DATA_QUERY_USER_ALL, DEV_DIRTY_CHECK_USER_ID_PREFIX + timestamp);
						pushCommand(sn, CMD_DATA_QUERY_FINGER_ALL, DEV_DIRTY_CHECK_FINGER_ID_PREFIX + timestamp);
						pushCommand(sn, CMD_DATA_QUERY_PHOTO_ALL, DEV_DIRTY_CHECK_PHOTO_ID_PREFIX + timestamp);
						pushCommand(sn, CMD_DATA_QUERY_FACE_ALL, DEV_DIRTY_CHECK_FACE_ID_PREFIX + timestamp);
						*/
						devicesTableLastStamp.put(sn + "-" + TAB_OPERLOG, "0");
						/*
						 * TODO 判断配置文件中,ISSYNCDATA(是否同步数据字段)=1时,就给执行脏检测
						 */
						//final Properties options = new Properties(props);
						final Properties defaults = load(ClockServlet.class, DEFAULT_CFG_LOCATION, null);
						String isSyncData= (String) defaults.get("IsSyncData");
						if(null != isSyncData && "1".equals(isSyncData)){
							//脏检查
							pushCommand(sn, CMD_CHECK, DEV_DIRTY_CHECK_ID_PREFIX + timestamp);
						}
					}
				}

			}

			/*-
			 * 获取当前设备指令队列中的指令, 并下发.
			 */
			final StringBuilder buff = new StringBuilder();
			final String[] commands = pullCommands(sn);
			/*
			for (int i = 0; i < commands.length; i++) {
				if (i > 0) {
					buff.append("\n");
				}
				buff.append(commands[i]);
			}
			*/
			for (final String cmd : commands) {
				buff.append(cmd).append("\n");
			}
			
			if (0 < buff.length()) {
				LOGGER.debug("向设备{}下发命令:{}", sn, buff);
			}
			
			// 如果没有任何指令就返回LOG指令.
			if (1 > buff.length()) {
				buff.append(String.format(CMD_LOG, "PING-" + timestamp));
			}


			doResponse(resp, buff.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LOGGER.info("请求获取给定考勤机指令-handlePull():" + e);
		}
	}

	/**
	 * 响应相应内容到客户端.
	 *
	 * @param resp
	 *            HttpResponse.
	 * @param content
	 *            响应内容.
	 * @throws IOException
	 *             IOException.
	 */
	private void doResponse(final HttpServletResponse resp, final String content) throws IOException {
		resp.setCharacterEncoding(charset.name());
		// 部分版本需要以下响应头, 会根据Content-Length来处理而不是Size.
		resp.setHeader("Server", "ZKTeco IClock Gateway");
		resp.setHeader("Content-Type", "text/plain");
		resp.setDateHeader("Date", new Date().getTime()); // 同步时间用.
		resp.setHeader("Accept-Ranges", "bytes");
		resp.setHeader("Content-Length", String.valueOf(content.length()));

		final PrintWriter writer = resp.getWriter(); // TODO encoding.
		writer.write(content);
		writer.flush();
	}

	/*
	 * *************************************************************** 设备信息上报.
	 * *************************************************************
	 */

	/**
	 * @param sn
	 * @param now
	 * @param item
	 */
	protected void handleClockItem(final String sn, final Date now, final String item) {
		final String[] infos = tokenizeToArray(item, "\t");
		if (4 > infos.length) {
			LOGGER.warn("Skip invalid clock item: {}", item);
			return; // skip invalid item.
		}

		final String pin = infos[0]; // 考勤号码
		final String time = infos[1]; // 考勤时间, yyyy-MM-dd HH:mm:ss

		// 考勤状态, 0-上班签到 1-下班签退 2-外出 3-外出返回 4-加班签到 5-加班签退 8-就餐开始 9-就餐结束
		final String status = infos[2];
		final String verify = infos[3]; // 验证方式, 0=密码, 1=指纹, 2=卡, 9=其他

		final Map<String, String> verifyMap = new HashMap<String, String>();
		verifyMap.put("0", "密码");
		verifyMap.put("1", "指纹");
		verifyMap.put("2", "卡");
		verifyMap.put("9", "其他");
		verifyMap.put("16", "脸纹");

		final String type = verifyMap.get(verify);
		LOGGER.info("系统存储从考勤机返回的考勤时间: {}" , time);
		final Date newDate=DateUtils.str2Date(time, DateUtils.DATETIME_FORMAT_YYYY_MM_DD_HHMMSS);
		LOGGER.info("系统格式化后的考勤机考勤时间: {}" , newDate);
		
		// 获取newDate的前一分钟值 , 为了过滤同一人短时间重复打卡的
		final Date beforeDate = TimeUtil.getTimeBeforeMinute(newDate, -1);
		LOGGER.info("系统格式化后的考勤机考勤时间的前一分钟: {}" , beforeDate);
		
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("employeeCode", pin);
		params.put("sn", sn);
		params.put("time", newDate);
		params.put("beforeDate", beforeDate);
		params.put("state",status);
		params.put("verify", verify);
		int count= signService.count(params);
		/*
		 * 当数据库里面有当前这条考勤记录,就不保存
		 */
		if(count == 0){
			// save
			Sign sign = new Sign();
			sign.setEmployeeCode(pin);
			sign.setSn(sn);
			sign.setTime(newDate);
			sign.setState(status);
			sign.setVerify(verify);
			sign.setDeleted("0");
			sign.setCreatedDate(new Date());

			signService.save(sign);
			
		}
		

		LOGGER.info("设备{} 在 {} 发生用户打卡, 用户 {}, 打卡方式 {}, 打卡状态: {}", sn, time, pin, (null != type ? type : verify),
				status);
	}

	protected void handleUserItem(final String sn, final Date now, final String item) {
		final String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(now);
		final Map<String, String> info = tokenizeToMap(item, "\t");
		final String pin = info.get("PIN"); // 考勤号码.
		final String name = info.get("Name"); // 用户姓名.
		final String pri = info.get("Pri"); // 权限: 14-管理员, 0-普通用户
		final String password = info.get("Passwd"); // 密码.
		final String card = info.get("Card"); // ID 卡号码.
												// 如果使用[]时则为hex16进制,否则为原始卡号
		final String grp = info.get("Grp"); // 组别(用于门禁)
		final String tz = info.get("TZ"); // 时段(用于门禁)
		final String verify = info.get("Verify"); // ??

		EmployeeDeviceInfo empl1 = new EmployeeDeviceInfo();
		empl1.setEmployeeCode(pin);
		empl1.setPri(pri);
		empl1.setPwd(password);
		empl1.setCard(card);
		int result = isChangedEmployeeInfo(1, empl1);
		// result =
		// {0:没有变动,1:服务器中没有数据,2:信息变动,3:头像信息没有存储(但是表中有员工信息记录),4:员工基本信息没有存储(但是表中有员工信息记录)}
		if (result == 1) {

			empl1.setCreatedDate(new Date());
			empl1.setDeleted("0");
			employeeDeviceInfoService.save(empl1);
			LOGGER.debug("设备{}上用户{}信息新增: 姓名: {}, 权限: {}, 密码: {}, 卡号: {}", sn, pin, name, pri, password, card, grp, tz,
					verify);
		} else if (result == 2 || result == 4) {

			EmployeeDeviceInfo employeeDeviceInfo = employeeDeviceInfoService.getInfoByEmployeeCode(pin);

			employeeDeviceInfo.setPri(pri);
			employeeDeviceInfo.setPwd(password);
			employeeDeviceInfo.setCard(card);

			employeeDeviceInfo.setLastModifiedDate(new Date());

			employeeDeviceInfoService.update(employeeDeviceInfo);

			LOGGER.debug("设备{}上用户{}信息发生变动: 姓名: {}, 权限: {}, 密码: {}, 卡号: {}", sn, pin, name, pri, password, card, grp, tz,
					verify);
		}
		/*
		 * //如果发生变动,就要更新 if (isChangedItem(sn, TAB_USERINFO, pin, item)) {
		 * 
		 * LOGGER.debug("设备{}上用户{}信息发生变动: 姓名: {}, 权限: {}, 密码: {}, 卡号: {}", sn,
		 * pin, name, pri, password, card, grp, tz, verify); }
		 */

		// 缓存设备信息, 用于删除脏检查.
		cacheDeviceItem(sn, TAB_USERINFO, pin, timestamp + "|" + item);
		cacheDeviceItem("*", TAB_USERINFO, pin, item);

		//pushMulticastCommand(sn, CMD_DATA_UPDATE_USER, "SYNC_UR-" + sn + "-" + timestamp, item);
	}

	protected void handleFingerprintItem(final String sn, final Date now, final String item) {
		try {

			final String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(now);
			final Map<String, String> info = tokenizeToMap(item, "\t");
			final String pin = info.get("PIN");
			final String fid = info.get("FID");
			final int size = toIntQuiet(info.get("Size"), 0);
			final String valid = info.get("Valid");
			final String fingerTmp = info.get("TMP");
			final String finger = 0 < size ? fingerTmp.substring(0, size) : fingerTmp;
			final String key = pin + "-" + fid;

			FingerFaceTemplate fft = new FingerFaceTemplate();

			fft.setEmployeeCode(pin);
			fft.setFid(fid);
			fft.setTmpSize(size);
			fft.setTmp(finger);
			fft.setType(1);
			fft.setValid(valid);

			/**
			 * 将设备上传的数据和数据库里面做比较 如果没有变动,就不做处理 如果发生变化,就做修改 如果服务器中没有这个数据,就插入数据库
			 */
			// result =
			// {0:没有变动,1:服务器中没有数据,2:信息变动,3:没有当前指纹标识或人脸标识(fid)的指纹或人脸信息(tmp),4:设备在该次上传的中tmp为空}
			int result = isChangedFingerItmp(fft);

			if (result == 1 || result == 3) {

				fft.setDeleted("0");
				fft.setCreatedDate(new Date());

				fingerFaceTemplateService.save(fft);
				LOGGER.debug("设备{}上用户{} 指纹 {} 发生变动, 有效性: {}, 指纹: {}", sn, pin, fid, valid, finger);
			} else if (result == 2) {
				FingerFaceTemplate oldFingerFaceTemplate = fingerFaceTemplateService
						.findInfoByEmployeeCodeAndTypeAndFid(pin, fid, 1);

				oldFingerFaceTemplate.setTmpSize(size);
				oldFingerFaceTemplate.setTmp(finger);
				oldFingerFaceTemplate.setFid(fid);
				oldFingerFaceTemplate.setValid(valid);
				fingerFaceTemplateService.update(oldFingerFaceTemplate);
				LOGGER.debug("设备{}上用户{} 指纹 {} 发生变动, 有效性: {}, 指纹: {}", sn, pin, fid, valid, finger);
			}

			/*
			 * if (isChangedItem(sn, TAB_FINGERTMP, key, item)) {
			 * LOGGER.debug("设备{}上用户{} 指纹 {} 发生变动, 有效性: {}, 指纹: {}", sn, pin,
			 * fid, valid, finger); }
			 */

			// 缓存设备信息, 用于删除脏检查.
			cacheDeviceItem(sn, TAB_FINGERTMP, key, timestamp + "|" + item);
			cacheDeviceItem("*", TAB_FINGERTMP, key, item);

			//pushMulticastCommand(sn, CMD_DATA_UPDATE_FINGER, "SYNC_FP" + sn + "-" + timestamp, item);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LOGGER.debug("设备{}上穿用户基本信息发生异常:{}", sn, e.getMessage());
		}
	}

	protected void handlePhotoItem(final String sn, final Date now, final String item) {
		try {

			final String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(now);
			final Map<String, String> info = tokenizeToMap(item, "\t");
			final String pin = info.get("PIN");
			final String filename = info.get("FileName");
			final int size = toIntQuiet(info.get("Size"), 0);
			final String content = info.get("Content");
			final String photo = 0 < size ? content.substring(0, size) : content;

			/*
			 * if (isChangedItem(sn, TAB_USERPIC, pin, item)) {
			 * LOGGER.info("设备{}上用户{} 头像 {} 发生变动, 内容: {}", sn, pin, filename,
			 * photo); }
			 */
			EmployeeDeviceInfo empl1 = new EmployeeDeviceInfo();
			empl1.setEmployeeCode(pin);
			empl1.setPic(photo);
			int result = isChangedEmployeeInfo(2, empl1);
			// result =
			// {0:没有变动,1:服务器中没有数据,2:信息变动,3:头像信息没有存储(但是表中有员工信息记录),4:员工基本信息没有存储(但是表中有员工信息记录)}
			if (result == 1) {

				empl1.setCreatedDate(new Date());
				empl1.setDeleted("0");
				
				empl1.setLastModifiedDate(new Date());
				
				employeeDeviceInfoService.save(empl1);
				LOGGER.info("设备{}上用户{} 头像 {} 新增, 内容: {}", sn, pin, filename, photo);
			} else if (result == 2 || result == 3) {
				EmployeeDeviceInfo employeeDeviceInfo = employeeDeviceInfoService.getInfoByEmployeeCode(pin);
				employeeDeviceInfo.setPic(photo);
				employeeDeviceInfo.setLastModifiedDate(new Date());

				employeeDeviceInfoService.update(employeeDeviceInfo);

				LOGGER.info("设备{}上用户{} 头像 {} 发生变动, 内容: {}", sn, pin, filename, photo);
			}

			cacheDeviceItem(sn, TAB_USERPIC, pin, timestamp + "|" + item);
			cacheDeviceItem("*", TAB_USERPIC, pin, item);

			//pushMulticastCommand(sn, CMD_DATA_UPDATE_PHOTO, "SYNC_PT-" + sn + "-" + timestamp, item);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LOGGER.debug("设备{}上穿用户头像信息发生异常:{}", sn, e.getMessage());
		}
	}

	protected void handleFaceItem(final String sn, final Date now, final String item) {
		try {

			final String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(now);
			final Map<String, String> info = tokenizeToMap(item, "\t");
			final String pin = info.get("PIN");
			final String fid = info.get("FID");
			final int size = toIntQuiet(info.get("SIZE"), 0);
			final String valid = info.get("VALID");
			final String faceTmp = info.get("TMP");
			final String face = 0 < size ? faceTmp.substring(0, size) : faceTmp;
			final String key = pin + '-' + fid;

			FingerFaceTemplate fft = new FingerFaceTemplate();

			fft.setEmployeeCode(pin);
			fft.setFid(fid);
			fft.setTmpSize(size);
			fft.setTmp(face);
			fft.setType(2);
			fft.setValid(valid);

			/**
			 * 将设备上传的数据和数据库里面做比较 如果没有变动,就不做处理 如果发生变化,就做修改 如果服务器中没有这个数据,就插入数据库
			 */
			// result =
			// {0:没有变动,1:服务器中没有数据,2:信息变动,3:没有当前指纹标识或人脸标识(fid)的指纹或人脸信息(tmp),4:设备在该次上传的中tmp为空}
			int result = isChangedFingerItmp(fft);

			if (result == 1 || result == 3) {

				fft.setDeleted("0");
				fft.setCreatedDate(new Date());

				fingerFaceTemplateService.save(fft);
				LOGGER.info("设备{}上用户{} 脸纹 {} 新增, 有效性: {}, 脸纹: {}", sn, pin, fid, valid, face);
			} else if (result == 2) {
				FingerFaceTemplate oldFingerFaceTemplate = fingerFaceTemplateService
						.findInfoByEmployeeCodeAndTypeAndFid(pin, fid, 2);

				oldFingerFaceTemplate.setTmpSize(size);
				oldFingerFaceTemplate.setTmp(face);
				oldFingerFaceTemplate.setFid(fid);
				oldFingerFaceTemplate.setValid(valid);
				fingerFaceTemplateService.update(oldFingerFaceTemplate);

				LOGGER.info("设备{}上用户{} 脸纹 {} 发生变动, 有效性: {}, 脸纹: {}", sn, pin, fid, valid, face);
			}

			/*
			 * if (isChangedItem(sn, TAB_FACE, key, item)) {
			 * LOGGER.info("设备{}上用户{} 脸纹 {} 发生变动, 有效性: {}, 脸纹: {}", sn, pin,
			 * fid, valid, face); }
			 */

			cacheDeviceItem(sn, TAB_FACE, pin + "-" + fid, timestamp + "|" + item);
			cacheDeviceItem("*", TAB_FACE, pin + "-" + fid, item);

			//pushMulticastCommand(sn, CMD_DATA_UPDATE_FACE, "SYNC_FE-" + sn + "-" + timestamp, item);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LOGGER.debug("设备{}上穿用户脸部模板信息发生异常:{}", sn, e.getMessage());
		}
	}

	/**
	 * 
	 * 说明 : 检查员工的指纹和人脸是否存在或发生变动
	 * 
	 * @param fingerFaceTemplate
	 * @return result =
	 *         {0:没有变动,1:服务器中没有数据,2:信息变动,3:没有当前指纹标识或人脸标识(fid)的指纹或人脸信息(tmp),4:设备在该次上传的中tmp为空}
	 * @author zhangshaung
	 * @dateTime 2017年4月19日 下午5:12:35
	 */
	private int isChangedFingerItmp(FingerFaceTemplate fingerFaceTemplate) {
		int result = 0;
		try {

			// 先查询当前员工是否有指纹信息
			List<FingerFaceTemplate> fingerList = fingerFaceTemplateService
					.selectByEmployeeCodeAndType(fingerFaceTemplate.getEmployeeCode(), fingerFaceTemplate.getType());

			if (fingerList.size() > 0) {
				FingerFaceTemplate finger = fingerFaceTemplateService.findInfoByEmployeeCodeAndTypeAndFid(
						fingerFaceTemplate.getEmployeeCode(), fingerFaceTemplate.getFid(),
						fingerFaceTemplate.getType());
				if (null != finger) {
					if (null != fingerFaceTemplate.getTmp()) {
						if (!fingerFaceTemplate.getTmp().equals(finger.getTmp())) {
							result = 2;
						} else {
							result = 0;
						}
					} else {
						result = 4;
					}
				} else {
					result = 3;
				}
			} else {
				result = 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
		return result;
	}

	/**
	 * 
	 * 说明 : 检查设备sn中员工key的信息是否变动
	 * 
	 * @param type
	 * @param employeeDeviceInfo
	 * @return result =
	 *         {0:没有变动,1:服务器中没有数据,2:信息变动,3:头像信息没有存储(但是表中有员工信息记录),4:员工基本信息没有存储(但是表中有员工信息记录)}
	 * @author zhangshaung
	 * @dateTime 2017年4月19日 下午5:12:35
	 */
    private int isChangedEmployeeInfo(final int type, final EmployeeDeviceInfo employeeDeviceInfo) {

        int result = 0;
        try {
            // 根据职员代码查询该员工是否已经离职,若离职则不插入职位模板, 修改于 20170531 am 9:51
            Employee employee = employeeService.findOne(employeeDeviceInfo.getEmployeeCode());
            if (null != employee) {
                EmployeeDeviceInfo oldEmployeeDeviceInfo = employeeDeviceInfoService
                    .getInfoByEmployeeCode(employeeDeviceInfo.getEmployeeCode());

                if (null != oldEmployeeDeviceInfo) {
                    int flag = 0;
                    if (type == 1) {
                        if (null == oldEmployeeDeviceInfo.getPri() && null == oldEmployeeDeviceInfo.getPwd()
                            && null == oldEmployeeDeviceInfo.getCard()) {
                            flag = 3;
                        } else {
                            if (null != employeeDeviceInfo.getPri()) {
                                if (!employeeDeviceInfo.getPri().equals(oldEmployeeDeviceInfo.getPri())) {
                                    flag = 1;
                                }
                            }

                            if (null != employeeDeviceInfo.getPwd()) {
                                if (!employeeDeviceInfo.getPwd().equals(oldEmployeeDeviceInfo.getPwd())) {
                                    flag = 1;
                                }
                            }

                            if (null != employeeDeviceInfo.getCard()) {
                                if (!employeeDeviceInfo.getCard().equals(oldEmployeeDeviceInfo.getCard())) {
                                    flag = 1;
                                }
                            }
                        }

                    } else if (type == 2) {
                        if (null != employeeDeviceInfo.getPic()) {
                            if (!employeeDeviceInfo.getPic().equals(oldEmployeeDeviceInfo.getPic())) {
                                flag = 1;
                            }
                        } else {
                            flag = 2;
                        }

                    }
                    if (flag == 0) {// 没有变化
                        result = 0;
                    } else if (flag == 1) {// 出现变化
                        result = 2;
                    } else if (flag == 2) {// 没有存储过照片
                        result = 3;
                    } else if (flag == 3) {// 没有存储过员工基本信息
                        result = 4;
                    }
                } else {
                    result = 1;
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }

	/**
	 * 
	 * 说明 : 检查设备sn中员工key的信息是否变动
	 * 
	 * @param sn
	 * @param tab
	 * @param key
	 * @param newItem
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月19日 上午11:44:33
	 */
	private boolean isChangedItem(final String sn, final String tab, final String key, final String newItem) {

		final String item = parseItem(getCachedDeviceItem(sn, tab, key));
		return null == item || !item.equals(newItem);
	}

	private String parseItem(final String item) {
		if (null != item) {
			final int i = item.indexOf('|');
			return 0 > i ? item : item.substring(i + 1);
		}
		return null;
	}

	protected void handleAdminstratorActionItem(final String sn, final Date now, final String item) {
		final String[] info = tokenizeToArray(item, "\t");
		final String opcode = info[0]; // 操作代码
		final String adminPin = info[1]; // 管理员id
		final String time = info[2]; // 操作时间
		final String target1 = info[3]; // 操作对象1.
		final String target2 = info[4]; // 操作对象2.
		final String target3 = info[5]; // 操作对象3.
		final String target4 = info[6]; // 操作对象4.

		onAdminstratorAction(sn, opcode, adminPin, time, target1, target2, target3, target4);
	}

	/**
	 * 设备管理员操作(当前设备并没有完全实现所有操作码, 仅能在进入菜单时调用).
	 *
	 * @param sn
	 *            设备编号.
	 * @param opcode
	 *            操作代码.
	 * @param pin
	 *            管理员编号.
	 * @param time
	 *            操作时间.
	 * @param target1
	 *            操作对象1.
	 * @param target2
	 *            操作对象2.
	 * @param target3
	 *            操作对象3.
	 * @param target4
	 *            操作对象4.
	 */
	protected void onAdminstratorAction(final String sn, final String opcode, final String pin, final String time,
			final String target1, final String target2, final String target3, final String target4) {
		LOGGER.debug("设备{} 上发生管理员操作, 管理员{} 时间 {} 执行管理操作", sn, pin, time);
	}

	/**
	 * 服务器下发命令在设备执行后反馈结果.
	 *
	 * @param sn
	 *            设备序列号.
	 * @param commandId
	 *            指令执行ID.
	 * @param returnCode
	 *            执行执行结果.
	 * @param commandType
	 *            指令类型.
	 */
	protected void handleCommandReturned(final String sn, final String commandId, final String returnCode,
			final String commandType) {
		if (null != commandId) {
			if (commandId.startsWith("PING-")) {
				LOGGER.debug("");
				return;
			}

			// 根据任务ID,修改设备SN未执行(state=1)的命令的状态为完成(state=3)
			/*-
			 * 如果是业务命令, 则应该对应的业务命令状态.
			 * 如果是业务命令, 不会包含-
			 */
			final int index = commandId.indexOf("-");
			if (0 > index) {
				if ("0".equals(returnCode)) {
					LOGGER.info("设备代码: {} 执行命令({})成功, 返回信息为{}", sn, commandId, commandType);
					taskService.updateStateById(Long.parseLong(commandId), 3, null);
				} else {
					LOGGER.warn("设备代码: {} 执行命令不成功, 异常代码为:{}, 返回信息为{}", sn, commandId, commandType);
				}
				return;
			}

			/**
			 * 更新命令状态
			 * 
			 * //根据任务ID,修改设备SN未执行(state=1)的命令的状态为处理中(state=2)
			 * taskService.updateStateById(task.getId(), 2, 1);
			 */
			if ("LOG".equals(commandType) && commandId.startsWith(DEV_INIT_OVER_ID_PREFIX)) {
				LOGGER.info("设备{} 从服务器初始化完成", sn);
				devices.put(sn, Boolean.TRUE);
			} else if ("CHECK".equals(commandType) && commandId.startsWith(DEV_DIRTY_CHECK_ID_PREFIX)) {
				final String dirtyCheckTimestamp = commandId.substring(DEV_DIRTY_CHECK_ID_PREFIX.length());
				doDirtyCheck(sn, dirtyCheckTimestamp);
			} else if ("DATA".equals(commandType) && commandId.startsWith(DEV_DIRTY_CHECK_FACE_ID_PREFIX)) {
				// face 是最后执行的一个命令, 因此这个命令的执行结束后执行脏检查.
				final String dirtyCheckTimestamp = commandId.substring(DEV_DIRTY_CHECK_FACE_ID_PREFIX.length());
				doDirtyCheck(sn, dirtyCheckTimestamp);
			}
		}
		LOGGER.debug("设备{}执行指令{}[{}], 结果:{}", sn, commandType, commandId, returnCode);
	}

	/*
	 * **************************************************** 下发指令到设备.
	 * **************************************************
	 */

	/**
	 * 下发给定指令到给定设备.(已同步数据库)
	 *
	 * @param sn
	 *            目标设备序列号.
	 * @param command
	 *            设备指令.
	 * @param args
	 *            指令参数.
	 */
	protected void pushCommand(final String sn, final String command, final String... args) {
		/*
		 * List<String> queue = commandQueue.get(sn); if (null == queue) { queue
		 * = new LinkedList<>(); commandQueue.put(sn, queue); }
		 * queue.add(String.format(command, args));
		 */

		/*
		 * 直接向任务表里面插入该条指令 TODO taskService
		 */
		try {
			Task task = new Task();

			task.setSn(sn);
			task.setState(1);

			/*-
			 * 将args[] 转换成String类型,中间用""隔开
			 */
			task.setArgs(StringUtil.stringArrToString(args, ""));	// FIXME
			task.setCommand(command);
			task.setCreatedDate(new Date());
			task.setDeleted("0");

			taskService.save(task);

		} catch (final Exception e) {
			LOGGER.error("设备{},向任务表中下达命令{} ,参数{} 报错!", sn, command, args);
			LOGGER.error("设备{},向任务表中下达命令{} ,参数{} 报错:{}", sn, command, args, e.getMessage());
			LOGGER.error("pushCommand error", e);
		}
	}

	/**
	 * 给所有设备下发指令.
	 *
	 * @param sn
	 *            来源设备.
	 * @param command
	 *            指令.
	 * @param args
	 *            指令参数.
	 */
	protected void pushMulticastCommand(final String sn, final String command, final String... args) {

		/*
		 * for (final Map.Entry<String, Boolean> dev : devices.entrySet()) {
		 * final String key = dev.getKey(); final Boolean value =
		 * dev.getValue(); final boolean online = Boolean.TRUE.equals(value);
		 * 
		 * if (online && !key.equals(sn)) { pushCommand(key, command, args); } }
		 */

		/**
		 * TODO 需要查询数据库中 需要查询数据库中所有的能接通(state = 2)的设备 来下发命令
		 */
		try {
			//Device device1=deviceService.selectByDeviceSn(sn);
			
			/*-
			 * 查询所有在线设备.
			 */
			final Map<String, Object> params = new HashMap<String, Object>();
//			params.put("state", 2);
			//params.put("signPointId", device1.getSignPointId());
			final List<Device> deviceList = deviceService.findMany(params);

			for (final Device device : deviceList) {
				/*-
				 * 给所有非当前设备下发同步命令.
				 */
				if (!device.getSn().equals(sn)) {
					/*需要先清除数据清除*/
					//clearUserInfoAll(device.getSn());
					/**
					 * TODO 需要在数据库中根据不同的命令,查询不同设备下的信息,拼接到命令上去
					 */
					/*
					List<String> paramList = null;
					if (command.equals(CMD_DATA_UPDATE_USER)) {
						// 修改员工基本信息
						paramList = deviceService.updateUserInfoDataBySn(device.getSn());
					} else if (command.equals(CMD_DATA_UPDATE_FINGER)) {
						// 修改指纹模板
						paramList = deviceService.updateUserFingerTmpDataBySn(device.getSn());
					} else if (command.equals(CMD_DATA_UPDATE_FACE)) {
						// 修改面部模板
						paramList = deviceService.updateUserFaceTmpDataBySn(device.getSn());
					} else if (command.equals(CMD_DATA_UPDATE_PHOTO)) {
						// 修改员工头像
						paramList = deviceService.updateUserPhoneDataBySn(device.getSn());
					}
					if(null !=paramList){
						for (String param:paramList) {
							pushCommand(device.getSn(), command, param);
						}
						
					}
					*/
					if (args.length > 2) {
						throw new IllegalStateException();
					}
					pushCommand(device.getSn(), command, args.length < 3 ? new String[] {args[1]} : new String[0]);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LOGGER.info("设备{},从任务表中取得命令{} ,参数{} 报错!", sn, command, args);
			LOGGER.info("设备{},从任务表中取得命令{} ,参数{} 报错{}", sn, command, args, e.getMessage());
		}
	}
	/**
	 * 
	 * 说明 : 
	 * 清除所有数据
	 * @param sn
	 * @author zhangshaung
	 * @dateTime 2017年4月25日 下午5:54:06
	 */
	public void clearUserInfoAll(String sn){
		Task clearTask =new Task();
		 clearTask.setId(null);
		 clearTask.setSn(sn);
		 clearTask.setState(1);
	        /**
	         * 将args[] 转换成String类型,中间用""隔开 
	         */
	        List<String>  clearParam=deviceService.updateUserInfoDataBySn(sn);
	        if(null != clearParam){
	        	for(String param : clearParam){
	        		clearTask.setArgs(param);
			        clearTask.setCommand(CommandWrapper.CMD_CLEAR_DATA);
			        clearTask.setCreatedDate(new Date());
			        clearTask.setDeleted("0");
			        
			        taskService.save(clearTask);
	        	}
	        	
	        }
	}
	
	protected int pushInitDataTo(final String sn) {
		/**
		 * TODO 需要查询数据库中
		 */
		final Set<String> userKeys = getCachedDeviceItemKeys("*", TAB_USERINFO);
		final Set<String> fingerKeys = getCachedDeviceItemKeys("*", TAB_FINGERTMP);
		final Set<String> photoKeys = getCachedDeviceItemKeys("*", TAB_USERPIC);
		final Set<String> faceKeys = getCachedDeviceItemKeys("*", TAB_FACE);
		// TODO
		return 0;
	}

	/**
	 * 
	 * 说明 : 清除当前考勤机的命令
	 * 
	 * @param sn
	 * @author zhangshaung
	 * @dateTime 2017年4月18日 下午4:21:25
	 */
	protected void clearCommands(final String sn) {
		final List<String> queue = commandQueue.get(sn);
		if (null != queue) {
			queue.clear();
		}
		try {

			/**
			 * 从数据库里面清除设备SN的命令任务
			 */
			int clearCount = taskService.chearCommandsBySn(sn);
			LOGGER.info("设备{}清除命令记录数为:{}", sn, clearCount);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LOGGER.info("设备{}清除命令失败:报错{}", sn, e.getMessage());
		}
	}

	/**
	 * 
	 * 说明 : 获得设备SN的命令列表
	 * 
	 * @param sn
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月18日 下午5:23:33
	 */
	protected String[] pullCommands(final String sn) {
		/*
		 * final List<String> queue = commandQueue.get(sn); if (null != queue) {
		 * final String[] commands = queue.toArray(new String[queue.size()]);
		 * queue.clear(); return commands; }
		 */
		try {

			// 从数据库中获取设备SN的命令列表
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("sn", sn);
			// 命令状态为未处理的(state=1)
			params.put("state", 1);
			// List<Task> commandList=taskService.findMany(params);
			// 从数据库中取前20条
			List<Task> commandList = taskService.findManyBeforeSize(params);
			List<String> queue = new ArrayList<String>();
			for (Task task : commandList) {
				if (null != task.getCommand()) {
					/**
					 * 将命令的ID 存到命令中 如 当前这条命令的ID=1 命令为"C:%s:DATA UPDATE USERINFO
					 * %s" 就要替换成:"C:1:DATA UPDATE USERINFO 1"
					 */
					String command = task.getCommand();
					if (null != command) {
						String args = task.getArgs();
						if (StringUtil.hasChinese(args)) {
//							args = new String(args.getBytes(), GB2312.name());
						}
						command = String.format(command, task.getId().toString(), args);
						queue.add(command);
					}
					/*
					String command = null == task.getCommand() ? task.getCommand()
							: task.getCommand().replaceFirst("%s", "" + task.getId().toString() + "");
					queue.add(String.format(command, task.getArgs()));
					*/
				}
				// 根据任务ID,修改设备SN未执行(state=1)的命令的状态为处理中(state=2)
				taskService.updateStateById(task.getId(), 2, null);
			}
			if (null != queue) {
				final String[] commands = queue.toArray(new String[queue.size()]);
				queue.clear();
				return commands;
			}

			return new String[0];
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LOGGER.info("设备{}获取命令列表失败,报错信息{}", sn, e.getMessage());
		}
		return new String[0];
	}

	private void cacheDeviceItem(final String sn, final String tab, final String key, final String value) {
		final String cacheKey = sn + "-" + tab;
		Map<String, String> cache = deviceDataCache.get(cacheKey);

		if (null == cache) {
			cache = new ConcurrentHashMap<>();
			deviceDataCache.put(cacheKey, cache);
		}
		cache.put(key, value);
	}

	private Set<String> getCachedDeviceItemKeys(final String sn, final String tab) {
		final Set<String> keys = new HashSet<>();
		final String cacheKey = sn + "-" + tab;
		final Map<String, String> cache = deviceDataCache.get(cacheKey);
		if (null != cache) {
			keys.addAll(cache.keySet());
		}
		return keys;
	}

	/**
	 * 
	 * 说明 : 得到设备sn中员工key的信息
	 * 
	 * @param sn
	 * @param tab
	 * @param key
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月19日 上午11:45:49
	 */
	private String getCachedDeviceItem(final String sn, final String tab, final String key) {
		final String cacheKey = sn + "-" + tab;
		final Map<String, String> cache = deviceDataCache.get(cacheKey);
		return null == cache ? null : cache.get(key);
	}

	private String evictCachedDeviceItem(final String sn, final String tab, final String key) {
		final String cacheKey = sn + "-" + tab;
		final Map<String, String> cache = deviceDataCache.get(cacheKey);
		return null == cache ? null : cache.remove(key);
	}

	/**
	 * 执行删除数据的脏检查.
	 *
	 * @param sn
	 *            设备序列号.
	 * @param checkTimestamp
	 *            脏检查要求上传所有数据的时间.
	 */
	private void doDirtyCheck(final String sn, final String checkTimestamp) {
		// 服务器中该设备中所有的用户, 在本次要求脏检查时没有上传的, 应该是该设备删除的用户.
		try {

			final String[] dirtyCheckTabs = { TAB_USERPIC, TAB_FACE, TAB_FINGERTMP, TAB_USERINFO };
			for (final String tab : dirtyCheckTabs) {
				final Set<String> keys = getCachedDeviceItemKeys(sn, tab);
				for (final String key : keys) {
					final String value = getCachedDeviceItem(sn, tab, key);
					final int pIndex = key.indexOf('-'); // pin or pin-fid
					final int tIndex = null != value ? value.indexOf('|') : -1;

					final String pin = 0 > pIndex ? key : key.substring(0, pIndex);
					final String fid = 0 > pIndex ? null : key.substring(pIndex + 1);
					final String timestamp = 0 > tIndex ? null : value.substring(0, tIndex);

					if (null == timestamp) {
						continue;
					}

					// checkTimestamp > timestamp
					if (0 < checkTimestamp.compareTo(timestamp)) {
						if (TAB_USERPIC.equals(tab)) {
							handleRemovePhotoItem(sn, pin);
						} else if (TAB_FACE.equals(tab)) {
							handleRemoveFaceItem(sn, pin, fid);
						} else if (TAB_FINGERTMP.equals(tab)) {
							handleRemoveFingerItem(sn, pin, fid);
						} else if (TAB_USERINFO.equals(tab)) {
							handleRemoveUserItem(sn, pin);
						} else {
							LOGGER.warn("Unsupported dirty check for table: {}", tab);
						}
						// TODO remove
						evictCachedDeviceItem(sn, tab, key);
						evictCachedDeviceItem("*", tab, key);
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			LOGGER.info("设备{},执行删除数据的脏检查,报错{}", sn, e.getMessage());
		}
	}

	protected void handleRemoveUserItem(final String sn, final String pin) {
		LOGGER.info("设备{}删除用户{}", sn, pin);
		try {

			// 先从数据库中查询应该在设备sn上签到的员工信息,如果没有查到,就给设备下达删除命令
			List<EmployeeDeviceInfoVO> list = deviceService.findEmployeeBySn(sn);
			boolean flag = true;
			if (list.size() > 0) {
				for (EmployeeDeviceInfoVO eInfoVO : list) {
					if (eInfoVO.getCode() == pin) {
						flag = false;
					}

					if (!flag) {
						break;
					}
				}

			}
			if (flag) {
				pushCommand(sn, CMD_DATA_DELETE_USER, "PIN=" + pin );
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LOGGER.info("设备{}删除用户{},报错{}", sn, pin, e.getMessage());
		}
	}

	protected void handleRemovePhotoItem(final String sn, final String pin) {
		LOGGER.info("设备{}删除头像{}", sn, pin);
		try {

			// 先从数据库中查询应该在设备sn上签到的员工头像信息,如果没有查到,就给设备下达删除命令
			List<EmployeeDeviceInfoVO> list = deviceService.findEmployeeBySn(sn);
			boolean flag = true;
			if (list.size() > 0) {
				for (EmployeeDeviceInfoVO eInfoVO : list) {
					if (eInfoVO.getCode() == pin && null != eInfoVO.getPic()) {
						flag = false;
					}

					if (!flag) {
						break;
					}
				}

			}
			if (flag) {
				pushCommand(sn, CMD_DATA_DELETE_PHOTO, "PIN=" + pin );
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			LOGGER.info("设备{}删除头像{},报错{}", sn, pin, e.getMessage());
		}

	}

	protected void handleRemoveFingerItem(final String sn, final String pin, final String fid) {
		LOGGER.info("设备{}删除指纹{}", sn, pin + "-" + fid);
		try {

			// 先从数据库中查询应该在设备sn上签到的员工指纹模板信息,如果没有查到,就给设备下达删除命令
			List<EmployeeDeviceFingerFaceVo> list = deviceService.findEmployeeFingerFaceBySn(sn, 1);
			boolean flag = true;
			if (list.size() > 0) {
				for (EmployeeDeviceFingerFaceVo eInfoVO : list) {
					if (eInfoVO.getCode() == pin && eInfoVO.getFid() == fid) {
						flag = false;
					}
					if (!flag) {
						break;
					}
				}

			}
			if (flag) {
				pushCommand(sn, CMD_DATA_DELETE_FINGER, "PIN=" + pin + "\t" + "FID=" + fid );
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LOGGER.info("设备{}删除指纹{},报错{}", sn, pin + "-" + fid, e.getMessage());
		}
	}

	protected void handleRemoveFaceItem(final String sn, final String pin, final String fid) {
		LOGGER.info("设备{}删除人脸{}", sn, pin + "-" + fid);

		try {

			// 先从数据库中查询应该在设备sn上签到的员工面部模板信息,如果没有查到,就给设备下达删除命令
			List<EmployeeDeviceFingerFaceVo> list = deviceService.findEmployeeFingerFaceBySn(sn, 2);
			boolean flag = true;
			if (list.size() > 0) {
				for (EmployeeDeviceFingerFaceVo eInfoVO : list) {
					if (eInfoVO.getCode() == pin && eInfoVO.getFid() == fid) {
						flag = false;
					}
					if (!flag) {
						break;
					}
				}

			}
			if (flag) {
				pushCommand(sn, CMD_DATA_DELETE_FACE, "PIN=" + pin + "\t" + "FID=" + fid);
			}
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("设备删除人脸失败!" + e.getMessage());
			e.printStackTrace();

		}
	}

	/**
	 * @param sn
	 *            设备序列号.
	 * @param info
	 *            设备第一次向服务器请求命令，或者设备新登记用户／指纹和有新的考勤记录时，URL路径格式中包含的INFO信息.
	 * @return boolean 是否为新设备:{ true :是新设备,false:不是新设备}
	 */
	protected boolean touch(final String sn, final String info) {
		try {
			Device device = deviceService.selectByDeviceSn(sn);
			Date date = new Date();
			/*-
			 * INFO=固件版本号,登记用户数,登记指纹数,考勤记录数,考勤机IP,指纹算法版本[,人脸算法版本,注册人脸时所需模板个数,登记人脸数,设备支持功能标识]
			 * 设备支持功能标识:
			 * 第一位: FP, 是否支持指纹下载, 1 支持,0不支持
			 * 第二位: FACE, 是否支持脸部下载
			 * 第三位: USERPIC, 是否支持用户照片下载
			 */
			if (null != info) {
				final String[] infoArray = tokenizeToArray(info, ",");
				final String version = 0 < infoArray.length ? infoArray[0] : null; // 固件版本.
				final String userCount = 1 < infoArray.length ? infoArray[1] : null; // 登记用户数.
				final String fingerCount = 2 < infoArray.length ? infoArray[2] : null; // 登记指纹数.
				final String transactionCount = 3 < infoArray.length ? infoArray[3] : null; // 考勤记录数.
				final String ip = 4 < infoArray.length ? infoArray[4] : null; // 设备IP地址.
				final String fingerVersion = 5 < infoArray.length ? infoArray[5] : null; // 指纹算法版本.
				final String faceVersion = 6 < infoArray.length ? infoArray[6] : null; // 人脸算法版本.
				final String faceSize = 7 < infoArray.length ? infoArray[7] : null; // 人脸算法所需模板个数.
				final String faceCount = 8 < infoArray.length ? infoArray[8] : null; // 登记人脸数.
				final String supported = 9 < infoArray.length ? infoArray[9] : null; // 支持功能标记.

				/**
				 * 先在数据库中检查,当前接入设备在数据库中有没有记录, 如果没有,就插入当前接入设备的信息
				 * 如果有,就更新数据库中的第一次连接的时间
				 */
				if (null == device) {

					device = new Device();

					device.setSn(sn);
					device.setName("中控考勤机-"+sn);
					device.setIp(ip);
					device.setFirstConnectionTime(date);
					device.setLastConnectionTime(date);
					device.setState(1);

					deviceService.saveDevice(device);
				} else {
					device.setIp(ip);
					//如果考勤点为空，就不更新同步人数
					Device device1=deviceService.selectSignPointNotNullByDeviceSn(sn);
					if(null != device1){
    					
    					 //应该同步的人数
    					int totalPeople = deviceService.countEmployeeBySn(sn);
    					device.setTotalPeople(totalPeople > 0 ? totalPeople : 0l);
    					
    					//设备已同步人数(这里获取的是设备上的人员数据,不能排查出脏数据,所有需要改动成手动获取的情况) 修改于2017/06/05 pm 14:00
                        Long syncPeople=null != userCount ? Long.parseLong(userCount) : 0l;
    					LOGGER.info("考勤机已同步的人数是: {}", syncPeople);
                        
    					//未同步人数
    					long unsyncPeople=totalPeople-syncPeople;
    					
    					// 修改于20170526 13:51 原来是等于,现发现考勤机上存在脏数据后,导致未同步人数为负数,所以修改为小于等于
    					if(unsyncPeople <= 0){
    						//如果同步人数大于0,同步状态为 已同步
    						device.setSyncState(3);
    					}else{
    						// 根据设备序列号查询该设备的更新命令是否已经执行完成
    						List<Task> list = taskService.findTaskByDeviceSn(sn);
    						if (null == list || list.size() < 1) {
    							// 命令执行完毕了但是还是不相等,说明有未同步的人
    							device.setSyncState(1);
							}
    						LOGGER.info("未同步的人数是: {}", unsyncPeople);
    						// 现阶段考勤机中如果存在脏数据不能够清除,所以为了让数据显示正确,需要手动查询一次
    						syncPeople = deviceService.syncPeopleCountEmployeeBySn(sn);
    						device.setUnsyncPeople(unsyncPeople > 0 ? unsyncPeople : 0l);
    					}
                    }
					//设备未同步数
					device.setLastConnectionTime(date);
					device.setLastModifiedDate(date);

					deviceService.updateByPrimaryKeySelective(device);
				}
				boolean finger = false; // 是否支持指纹下载.
				boolean face = false; // 是否支持脸部下载.
				boolean userPic = false; // 是否支持用户照片下载.
				if (null != supported && 2 < supported.length()) {
					finger = supported.charAt(0) - '0' > 0;
					face = supported.charAt(1) - '0' > 0;
					userPic = supported.charAt(2) - '0' > 0;
				}

				return true;
			} else {
				/*
				 * TODO 修改设备表的状态,链接时间
				 */
				
				if (null != device ) {
					/**
					 * 检查当前设备是否已使用 (signPointId 不等于null) 
					 * #已注释 修改状态放在HR那边
					 */
					Device signPointNotNullDevice = deviceService.selectSignPointNotNullByDeviceSn(sn);
					if(null != signPointNotNullDevice){
						device.setState(2);
					}
					device.setLastConnectionTime(date);
					device.setLastModifiedDate(date);

					// deviceService.update(device);
					deviceService.updateByPrimaryKeySelective(device);
				}
				return false;
			}

		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("touch 方法异常 :{}", e.getMessage());
			e.printStackTrace();
		}

		return false;
	}

	/*
	 * ************************************************************ Help
	 * Methods. **********************************************************
	 */

	/**
	 * 解析给定的整数字符串, 如果无法解析返回默认值.
	 *
	 * @param text
	 *            int字符串.
	 * @param defaultValue
	 *            默认值.
	 * @return 字符串表示的int值或默认值.
	 */
	private int toIntQuiet(final String text, final int defaultValue) {
		if (null == text || 1 > text.length()) {
			return defaultValue;
		}
		try {
			return Integer.valueOf(text);
		} catch (final NumberFormatException ex) {
			return defaultValue;
		}
	}

	/**
	 * 如果给定值有内容, 则返回给定内容, 否则返回"0".
	 *
	 * @param value
	 *            值内容.
	 * @return 值.
	 */
	private String nvl(final String value) {
		return null == value || 1 > value.length() ? UNSET : value;
	}

	/**
	 * 将字符串通过给定分隔符字符拆分为数组.
	 *
	 * @param text
	 *            待处理字符串
	 * @param delim
	 *            分隔符字符(可多个)
	 * @return 拆分后的数组.
	 */
	private String[] tokenizeToArray(final String text, final String delim) {
		final List<String> ret = new ArrayList<String>();
		final StringTokenizer tokenizer = new StringTokenizer(text, delim, false);

		while (tokenizer.hasMoreTokens()) {
			ret.add(tokenizer.nextToken());
		}
		return ret.toArray(new String[ret.size()]);
	}

	/**
	 * 将给定的key=value字符串拆分为Map.
	 *
	 * @param pairs
	 *            key=value字符串.
	 * @param delim
	 *            多个key=value的分隔符.
	 * @return 拆分结果.
	 */
	private Map<String, String> tokenizeToMap(final String pairs, final String delim) {
		final Map<String, String> ret = new HashMap<String, String>();
		final StringTokenizer tokenizer = new StringTokenizer(pairs, delim, false);

		while (tokenizer.hasMoreTokens()) {
			final String token = tokenizer.nextToken();
			final int i = token.indexOf('=');
			if (0 > i) {
				ret.put(token, null);
			} else {
				ret.put(token.substring(0, i), token.substring(i + 1));
			}
		}
		return ret;
	}

	/**
	 * 加载配置文件.
	 *
	 * @param loader
	 *            加载资源使用的类.
	 * @param location
	 *            资源位置.
	 * @param defaults
	 *            默认配置.
	 * @return 如果资源正确加载返回配置否则返回null.
	 */
	private Properties load(final Class<?> loader, final String location, final Properties defaults) {
		InputStream in;
		if (location.startsWith(CLASSPATH_PREFIX)) {
			String loc = location.substring(CLASSPATH_PREFIX.length());
			loc = loc.startsWith("/") ? loc.substring(1) : loc;

			in = loader.getClassLoader().getResourceAsStream(loc);
		} else {
			in = getServletContext().getResourceAsStream(location);
			if (null == in) {
				in = loader.getClassLoader().getResourceAsStream(location);
			}
			if (null == in) {
				in = loader.getResourceAsStream(location);
			}
		}

		Properties props = null;
		if (null != in) {
			Reader reader = null;
			try {
				props = new Properties(defaults);
				reader = new InputStreamReader(in, UTF_8);
				props.load(reader);
			} catch (final IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (null != reader) {
						reader.close();
					}
				} catch (final IOException quiet) {
					// ignore
				}
				try {
					in.close();
				} catch (final IOException quiet) {
					// ignore
				}
			}
		}
		return props;
	}
}
