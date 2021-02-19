package com.demo.server.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.demo.server.entity.*;
import com.demo.server.result.Result;
import com.demo.server.result.ResultPage;
import com.demo.server.service.*;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhul
 * @since 2021-02-03
 */
@RestController
@RequestMapping("/employee/basic")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private NationService nationService;
    @Autowired
    private PoliticsStatusService politicsStatusService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private JoblevelService joblevelService;

    @ApiOperation(value = "获取所有员工信息（分页）")
    @GetMapping("/")
    public ResultPage getEmpployeeList(@RequestParam(defaultValue = "1") Integer currentPage,
                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                       @ModelAttribute Employee employee,
                                       LocalDate[] dateScope){
        return employeeService.getEmployeeList(currentPage,pageSize,employee,dateScope);
    }

    @ApiOperation(value = "新增员工信息")
    @PostMapping("/addEmployeeRecord")
    public Result addEmployee(@RequestBody Employee employee){
        Result result = employeeService.addEmployyeeRecord(employee);
        return result;
    }

    @ApiOperation(value = "更新员工信息")
    @PutMapping("/updateEmployeeRecord")
    public Result updateEmployeeRecord(@RequestBody Employee employee){
        if(employeeService.updateById(employee)){
            return Result.ok();
        }
        return Result.error();
    }

    @ApiOperation(value = "删除员工信息")
    @PostMapping("/deleteEmployeeRecord/{id}")
    public Result deleteEmployeeRecord(@PathVariable Integer id){
        if(employeeService.removeById(id)){
            return Result.ok();
        }
        return Result.error();
    }

    @ApiOperation(value = "导出员工信息")
    @GetMapping(value = "/exportEmployee",produces = "application/octet-stream")
    public void exportEmployee(HttpServletResponse response){
        // 先查询出所有的员工信息
        List<Employee> list = employeeService.getAllEmployeeList(null);
        // 创建workbook
        ExportParams exportParams = new ExportParams("员工信息表", "员工信息", ExcelType.HSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, Employee.class, list);
        // 创建输出流
        ServletOutputStream out = null;
        try {
            response.setHeader("content-type","application/octet-stream");
            // 防止中文乱码
            response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode("员工信息表.xls","utf-8"));
            out = response.getOutputStream();
            workbook.write(out);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                out.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @ApiOperation(value = "导入员工信息")
    @PostMapping("/importEmployees")
    public Result importEmployees(MultipartFile file){
        ImportParams params = new ImportParams();
        // 去掉标题行
        params.setTitleRows(1);
        // 获取民族、职称、职位、政治面貌、部门信息(重写hashcode方法后，根据名称获取id)
        List<Nation> nations = nationService.list();
        List<Department> departments = departmentService.list();
        List<Position> positions = positionService.list();
        List<PoliticsStatus> politicsStatuses = politicsStatusService.list();
        List<Joblevel> joblevels = joblevelService.list();

        try {
            List<Employee> employees = ExcelImportUtil.importExcel(file.getInputStream(), Employee.class, params);
            employees.forEach(employee -> {
                // 获取民族的名称,根据名称在hash表中查找hash下标并找到对应的nationId
                employee.setNationId(nations.get(nations.indexOf(employee.getNation())).getId());
                employee.setJobLevelId(joblevels.get(joblevels.indexOf(employee.getJoblevel())).getId());
                employee.setPoliticId(politicsStatuses.get(politicsStatuses.indexOf(employee.getPoliticsStatus())).getId());
                employee.setPosId(positions.get(positions.indexOf(employee.getPosition())).getId());
                employee.setDepartmentId(departments.get(departments.indexOf(employee.getDepartment())).getId());
            });
            if(employeeService.saveBatch(employees)){
                return Result.ok();
            }else {
                return Result.error();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value = "获取民族信息")
    @GetMapping("/nation")
    public List<Nation> getNationList(){
        List<Nation> list = nationService.list();
        return list;
    }

    @ApiOperation(value = "获取职位信息")
    @GetMapping("/position")
    public List<Position> getPositionList(){
        List<Position> list = positionService.list();
        return list;
    }

    @ApiOperation(value = "获取职称信息")
    @GetMapping("/joblevel")
    public List<Joblevel> getJoblevelList(){
        List<Joblevel> list = joblevelService.list();
        return list;
    }

    @ApiOperation(value = "获取政治面貌信息")
    @GetMapping("/politicsStatus")
    public List<PoliticsStatus> getPoliticsStatusList(){
        List<PoliticsStatus> list = politicsStatusService.list();
        return list;
    }

    @ApiOperation(value = "获取部门信息")
    @GetMapping("/deparments")
    public List<Department> getDepartmentList(){
        List<Department> list = departmentService.list();
        return list;
    }

    @ApiOperation(value = "获取员工工号")
    @GetMapping("/getWorkId")
    public Result maxWorkID(){
        Result result = employeeService.maxWorkID();
        return result;
    }



}
