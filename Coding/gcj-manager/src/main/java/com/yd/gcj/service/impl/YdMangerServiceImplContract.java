package com.yd.gcj.service.impl;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerContract;
import com.yd.gcj.entity.YdMangerFiles;
import com.yd.gcj.entity.YdMangerFilesTask;
import com.yd.gcj.entity.YdMangerTaskFolder;
import com.yd.gcj.mapper.YdMangerMapperContract;
import com.yd.gcj.mapper.YdMangerMapperFiles;
import com.yd.gcj.mapper.YdMangerMapperFilesTask;
import com.yd.gcj.mapper.YdMangerMapperTask;
import com.yd.gcj.mapper.YdMangerMapperTaskFolder;
import com.yd.gcj.service.YdMangerServiceContract;
import com.yd.gcj.tool.MapInitFactory;
import com.yd.gcj.tool.Values;

@Service("YdMangerServiceContract")
public class YdMangerServiceImplContract implements YdMangerServiceContract {

	@Autowired
	private YdMangerMapperContract ydMangerMapperContract;

	@Autowired
	private YdMangerMapperTask ydMangerMapperTask;
	
	@Autowired
	private YdMangerMapperTaskFolder mapperFolder;
	
	@Autowired
	private YdMangerMapperFiles mapperFiles;
	
	@Autowired
	private YdMangerMapperFilesTask mapperFilesTask;
	
	@Override
	public YdMangerContract $queryById(Integer contract_id) {
		return ydMangerMapperContract.$queryById(contract_id);
	}

	@Override
	public YdMangerContract $queryBySql(String sql) {
		return ydMangerMapperContract.$queryBySql(sql);
	}

	@Override
	public Object $insert(YdMangerContract contract) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		mapInitFactory.setSystemError();
		Integer isExsit = ydMangerMapperContract.$isExsit(contract.getContract_tid());
		if (isExsit > 0) {
			Integer isOk = ydMangerMapperTask.$updateContractState(contract.getContract_tid(), 1);
			if (isOk > 0) {
				mapInitFactory.setMsg(Values.INITSUCCESSCODE, "确认成功！");
			} else {
				mapInitFactory.setMsg("503", "提交失败！");
			}
		} else {
			mapInitFactory.setMsg("502", "提交失败！");
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object $update(YdMangerContract contract, Integer userType, Integer taskState) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		mapInitFactory.setSystemError();
		mapInitFactory.setData(0);
		// 合同状态为设置为服务商已编辑
		Integer taskContractState = 2;

		Integer success = ydMangerMapperContract.$update(contract);
		if (success > 0) {
			Integer taskStateSuccess = ydMangerMapperTask.$updateContractState(contract.getContract_tid(),
					taskContractState);
			if (taskStateSuccess > 0) {
				mapInitFactory.setMsg("200", "合同确认成功！");
				mapInitFactory.setData(4);
			} else {
				mapInitFactory.setMsg("502", "合同确认失败！");
			}
		} else {
			mapInitFactory.setMsg("501", "合同确认失败！");
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Integer $delete(Integer contract_id) {
		return ydMangerMapperContract.$delete(contract_id);
	}

	@Override
	public YdMangerContract $queryByTaskId(Integer taskId) {
		return ydMangerMapperContract.$queryByTid(taskId);
	}

	@Override
	public Object $eSign(YdMangerContract contract, Integer contractState) {
		MapInitFactory mif = new MapInitFactory();
		mif.setSystemError();
		Integer isExist = ydMangerMapperContract.$isExsit(contract.getContract_tid());
		if (isExist > 0) {
			contract.setContract_update_time(new Date());
			Integer taskContractState = 5;
			if (contractState == 6) {
				taskContractState = 7;
			}
			Integer success = ydMangerMapperContract.$eSign(contract);
			if (success > 0) {
				Integer taskStateSuccess = ydMangerMapperTask.$updateContractState(contract.getContract_tid(),
						taskContractState);
				if (taskStateSuccess > 0) {
					mif.setMsg("200", "签订成功！");
				} else {
					mif.setMsg("502", "签订失败！");
				}
			} else {
				mif.setMsg("501", "签订失败！");
			}
		} else {
			mif.setMsg("502", "合同信息不存在！");
		}
		return mif.getMap();
	}

	@Override
	public Object $sSign(YdMangerContract contract, Integer contractState) {
		MapInitFactory mif = new MapInitFactory();
		mif.setSystemError();
		Integer isExist = ydMangerMapperContract.$isExsit(contract.getContract_tid());
		if (isExist > 0) {
			contract.setContract_update_time(new Date());
			Integer success = ydMangerMapperContract.$sSign(contract);
			if (success > 0) {
				Integer taskContractState = 6;
				if (contractState == 5) {
					taskContractState = 7;
				}
				Integer taskStateSuccess = ydMangerMapperTask.$updateContractState(contract.getContract_tid(),
						taskContractState);
				if (taskStateSuccess > 0) {
					mif.setMsg("200", "签订成功！");
				} else {
					mif.setMsg("502", "签订失败！");
				}
			} else {
				mif.setMsg("501", "签订失败！");
			}
		} else {
			mif.setMsg("502", "合同信息不存在！");
		}
		return mif.getMap();
	}

	@Override
	public Integer supplementaryContract(@Param("contractId") Integer contractId,
			@Param("contractSupp") String contractSupp) {
		return ydMangerMapperContract.supplementaryContract(contractId, contractSupp);
	}
	
	@Override
	public Object saveContractFile(Integer taskId, String path,String name) {
		MapInitFactory mf = new MapInitFactory();
		Integer isExsit = mapperFolder.isExistByName("合同文件", taskId);
		YdMangerTaskFolder folder = null;
		if(isExsit == 0){
			folder = new YdMangerTaskFolder();
			folder.setF_name("合同文件");
			folder.setF_tid(taskId);
			folder.setF_create_time(new Date());
			mapperFolder.insert(folder);
		}else{
			folder = mapperFolder.queryByName("合同文件", taskId);
		}
		if(folder != null){
			YdMangerFiles files = new YdMangerFiles();
			files.setFiles_desc("合同文件");
			files.setFiles_name(name);
			files.setFiles_path(path);
			files.setFiles_size(0);
			files.setFiles_create_time(new Date());
			mapperFiles.$insert(files);
			
			YdMangerFilesTask filesTask = new YdMangerFilesTask();
			filesTask.setFiletr_fid(folder.getF_id());
			filesTask.setFiletr_id(files.getFiles_id());
			filesTask.setFiletr_tid(taskId);
			mapperFilesTask.$insert(filesTask);
			
			mf.setMsg("200", "上传成功！");
		}else{
			mf.setMsg("501", "上传失败！");
		}
		
		return mf.getMap();
	}

}
