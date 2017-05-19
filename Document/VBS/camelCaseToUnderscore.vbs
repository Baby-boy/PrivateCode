Option Explicit
ValidationMode = True
InteractiveMode = im_Abort

Function camelCaseToUnderscore(str)
   Dim result, l, i, c, p
   '  特殊处理
   IF "TB" = LEFT(str, 2) THEN
      result = "TB_"
      str = RIGHT(str, LEN(str) - 1)
   ELSE
      result = UCase(LEFT(str, 1))
   END IF
   
   l = LEN(str)
   
   For i = 2 to l
      p = RIGHT(LEFT(str, i - 1), 1)
      c = RIGHT(LEFT(str, i), 1)
      IF c = UCase(c) THEN
         IF p = LCase(p) AND i < l THEN
            result = result & "_"
         END IF
         result = result & UCase(c)
      ELSE
         result = result & UCase(c)
      END IF
   Next
   camelCaseToUnderscore = result
End Function


Dim mdl ' 定义当前的模型

'通过全局参数获得当前的模型
Set mdl = ActiveModel
If (mdl Is Nothing) Then
   MsgBox "没有选择模型，请选择一个模型并打开."
ElseIf Not mdl.IsKindOf(PdPDM.cls_Model) Then
   MsgBox "当前选择的不是一个物理模型（PDM）."
Else
   ProcessFolder mdl
End If


'--------------------------------------------------------------------------------
'功能函数
'--------------------------------------------------------------------------------
Private Sub ProcessFolder(folder)
   Dim Tab '定义数据表对象
   for each Tab in folder.tables
      if not tab.isShortcut then
         'if tab.comment = "" then tab.comment = tab.name '进行判断并赋值
         tab.code = camelCaseToUnderscore(tab.code)
         Dim col '定义列对象
         for each col in tab.columns
            'if col.comment = "" then col.comment = col.name '进行判断并赋值
            col.code = camelCaseToUnderscore(col.code)
            'col.dataType = camelCaseToUnderscore(col.dataType)
         next
      end if
   next
   
   '对子包进行递归，如果不使用递归只能取到第一个模型图内的表
   dim subfolder
   for each subfolder in folder.Packages
      ProcessFolder subfolder
   next

   'msgbox "完成把comment为空的内容用name代替"
End Sub
