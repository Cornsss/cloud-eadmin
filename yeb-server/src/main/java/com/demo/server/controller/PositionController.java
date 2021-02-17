package com.demo.server.controller;


import com.demo.server.entity.Position;
import com.demo.server.result.Result;
import com.demo.server.result.ResultCode;
import com.demo.server.service.PositionService;
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
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @ApiOperation(value = "获取所有职位信息")
    @GetMapping("/")
    public List<Position> getAllPositions(){
        return positionService.list();
    }

    @ApiOperation(value = "添加职位信息")
    @PostMapping("/")
    public Result addPositions(@RequestBody Position position){
        Result result = Result.ok();
        position.setCreateDate(LocalDateTime.now());
        if(positionService.save(position)){
            return Result.ok();
        }
        result.setMessage(ResultCode.ERROR.getMessage());
        result.setCode(ResultCode.ERROR.getCode());
        return result;
    }

    @ApiOperation(value = "更新职位信息")
    @PutMapping("/")
    public Result updatePositions(@RequestBody Position position){
        Result result = Result.ok();
        if(positionService.updateById(position)){
            return Result.ok();
        }
        result.setMessage(ResultCode.ERROR.getMessage());
        result.setCode(ResultCode.ERROR.getCode());
        return result;
    }

    @ApiOperation(value = "删除职位信息")
    @DeleteMapping("/{id}")
    public Result deletePosition(@PathVariable Integer id){
        Result result = Result.ok();
        if(positionService.removeById(id)){
            return Result.ok();
        }
        result.setMessage(ResultCode.ERROR.getMessage());
        result.setCode(ResultCode.ERROR.getCode());
        return result;
    }

    @ApiOperation(value = "批量删除职位信息")
    @DeleteMapping("/")
    public Result deletePositionByIds(Integer[] ids){
        Result result = Result.ok();
        if(positionService.removeByIds(Arrays.asList(ids))){
            return Result.ok();
        }
        result.setMessage(ResultCode.ERROR.getMessage());
        result.setCode(ResultCode.ERROR.getCode());
        return result;
    }

}
