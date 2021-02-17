package com.demo.server.controller;


import com.demo.server.entity.Joblevel;
import com.demo.server.entity.Position;
import com.demo.server.result.Result;
import com.demo.server.result.ResultCode;
import com.demo.server.service.JoblevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
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
@RequestMapping("/system/basic/joblevel")
public class JoblevelController {

    @Autowired
    private JoblevelService joblevelService;

    @ApiOperation(value = "获取所有职称信息")
    @GetMapping("/")
    public List<Joblevel> getAllJoblevel(){
        return joblevelService.list();
    }

    @ApiOperation(value = "添加职称信息")
    @PostMapping("/")
    public Result addJoblevels(@RequestBody Joblevel joblevel){
        Result result = Result.ok();
        joblevel.setCreateDate(LocalDateTime.now());
        if(joblevelService.save(joblevel)){
            return Result.ok();
        }
        result.setMessage(ResultCode.ERROR.getMessage());
        result.setCode(ResultCode.ERROR.getCode());
        return result;
    }

    @ApiOperation(value = "更新职称信息")
    @PutMapping("/")
    public Result updateJoblevels(@RequestBody Joblevel joblevel){
        Result result = Result.ok();
        if(joblevelService.updateById(joblevel)){
            return Result.ok();
        }
        result.setMessage(ResultCode.ERROR.getMessage());
        result.setCode(ResultCode.ERROR.getCode());
        return result;
    }

    @ApiOperation(value = "删除职称信息")
    @DeleteMapping("/{id}")
    public Result deleteJoblevel(@PathVariable Integer id){
        Result result = Result.ok();
        if(joblevelService.removeById(id)){
            return Result.ok();
        }
        result.setMessage(ResultCode.ERROR.getMessage());
        result.setCode(ResultCode.ERROR.getCode());
        return result;
    }

    @ApiOperation(value = "批量删除职称信息")
    @DeleteMapping("/")
    public Result deleteJoblevelsByIds(Integer[] ids){
        Result result = Result.ok();
        if(joblevelService.removeByIds(Arrays.asList(ids))){
            return Result.ok();
        }
        result.setMessage(ResultCode.ERROR.getMessage());
        result.setCode(ResultCode.ERROR.getCode());
        return result;
    }

}
