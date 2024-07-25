package com.scoks.order.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sargeraswang.util.ExcelUtil.ExcelUtil;
import com.scoks.order.Constant;
import com.scoks.order.dto.ExportOrderDTO;
import com.scoks.order.dto.OrderMaterialLackDTO;
import com.scoks.order.dto.OrderMaterialSumDTO;
import com.scoks.order.dto.OrderQuery;
import com.scoks.order.entity.*;
import com.scoks.order.exception.Result;
import com.scoks.order.exception.ResultException;
import com.scoks.order.exception.ResultStatus;
import com.scoks.order.query.OrderMaterialQuery;
import com.scoks.order.service.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/scoks/api/order")
@Validated
@Slf4j
public class OrderController extends BaseController {

    @Autowired
    private OrderServiceImpl orderService;

    @RequestMapping(value = "order_list", method = RequestMethod.GET)
    @RequiresAuthentication
    public Object queryAdminList(Page<Order> page, OrderQuery form) {
        return orderService.findOrderPageList(page, form);
    }


    @RequiresAuthentication
    @GetMapping(value = "/order_edit")
    public Object order_edit(@RequestParam(value = "id") Long id) {
        return orderService.getOrderById(id);
    }


    @RequiresAuthentication
    @RequiresRoles(value = {Constant.POSITION_DIRECTOR})
    @PostMapping(value = "/order_edit")
    public Result order_edit(Order form) throws Exception {
        return Result.success(orderService.setOrder(form, getLoginUser().getId()));
    }

    @RequiresAuthentication
    @RequiresRoles(value = {Constant.POSITION_DIRECTOR})
    @PostMapping(value = "/order_delete")
    public Result order_delete(Long id) throws Exception {
        Order deleteOrder = new Order();
        deleteOrder.setId(id);
        deleteOrder.setState(1);
        orderService.setOrder(deleteOrder, getLoginUser().getId());
        return Result.success(null);
    }


    @RequiresAuthentication
    @RequiresRoles(value = {Constant.POSITION_DIRECTOR})
    @PostMapping(value = "/production_completed")
    public Result order_status(Long id, Integer state) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(id);
        orderStatus.setProduceState(state);
        orderService.setOrderStatus(orderStatus);
        return Result.success(null);
    }

    @RequiresAuthentication
    @RequiresRoles(value = {Constant.POSITION_FINALIZE})
    @PostMapping(value = "/finalize_completed")
    public Result finalize_completed(Long id, Integer state) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(id);
        orderStatus.setFinalizeState(state);
        orderService.setOrderStatus(orderStatus);
        return Result.success(null);
    }

    @RequestMapping(value = "product_edit", method = RequestMethod.GET)
    @RequiresAuthentication
    public Object getProduct(Long id) {
        return Result.success(orderService.getOrderProduct(id));
    }

    @RequestMapping(value = "product_edit", method = RequestMethod.POST)
    @RequiresRoles(value = {Constant.POSITION_DIRECTOR})
    @RequiresAuthentication
    public Object edit_pruduct(OrderProduct form) throws Exception {
        orderService.setOrderPruduct(form);
        return Result.success(null);
    }

    @RequestMapping(value = "product_delete", method = RequestMethod.POST)
    @RequiresRoles(value = {Constant.POSITION_DIRECTOR})
    @RequiresAuthentication
    public Object product_delete(Long id) throws Exception {
        OrderProduct deleteProduct = new OrderProduct();
        deleteProduct.setId(id);
        deleteProduct.setState(1);
        orderService.setOrderPruduct(deleteProduct);
        return Result.success(null);
    }

    @RequestMapping(value = "work_list", method = RequestMethod.GET)
    @RequiresAuthentication
    public Object queryWorkList(@Valid OrderProductWork form, BindingResult bindingResult, Page<OrderProductWork> page) {
        return orderService.findOrderProductWorkPageList(page, form);
    }


    @RequiresAuthentication
    @RequiresRoles(value = {Constant.POSITION_DIRECTOR, Constant.POSITION_BOSS}, logical = Logical.OR)
    @PostMapping(value = "/work_edit")
    public Object work_edit(@Valid OrderProductWork form) {
        Staff loginUser = getLoginUser();
        if (form.getId() == null) {
            form.setCreator(loginUser.getId());
        }
        orderService.setOrderProductWork(form);
        return Result.success(null);
    }

    @RequiresAuthentication
    @RequiresRoles(value = {Constant.POSITION_DIRECTOR, Constant.POSITION_BOSS}, logical = Logical.OR)
    @PostMapping(value = "/work_batch")
    public Object work_edit(@RequestBody List<OrderProductWork> works) {
        Staff loginUser = getLoginUser();
        for (OrderProductWork orderProductWork : works)
            if (orderProductWork.getId() == null) {
                orderProductWork.setCreator(loginUser.getId());
                orderService.setOrderProductWork(orderProductWork);
            }
        return Result.success(null);
    }

    @RequiresAuthentication
    @RequiresRoles(value = {Constant.POSITION_DIRECTOR})
    @PostMapping(value = "/work_delete")
    public Object work_delete(Long id) {
        if (id == null) {
            throw new ResultException(ResultStatus.PARAM_ERR);
        }
        OrderProductWork orderProductWork = new OrderProductWork();
        orderProductWork.setId(id);
        orderProductWork.setState(1);
        orderService.setOrderProductWork(orderProductWork);
        return Result.success(null);
    }


    @RequestMapping(value = "finalize_list", method = RequestMethod.GET)
    @RequiresAuthentication
    public Object query_finalize_list(@Valid OrderProductFinalize form, BindingResult bindingResult, Page<OrderProductFinalize> page) {
        return orderService.findOrderProductFinalizePageList(page, form);
    }

    @RequestMapping(value = "finalize_edit", method = RequestMethod.GET)
    @RequiresAuthentication
    public Object finalize_edit(Long id) {
        return orderService.getOrderFinalize(id);
    }

    @RequiresAuthentication
    @PostMapping(value = "/finalize_edit")
    @RequiresRoles(value = {Constant.POSITION_FINALIZE})
    public Object finalize_edit(@Valid OrderProductFinalize form) {
        Staff loginUser = getLoginUser();
        if (form.getId() == null) {
            form.setCreator(loginUser.getId());
        }
        orderService.setOrderProductFinalize(form);
        return Result.success(null);
    }

    @RequiresAuthentication
    @PostMapping(value = "/finalize_delete")
    @RequiresRoles(value = {Constant.POSITION_FINALIZE})
    public Object finalize_delete(Long id) {
        if (id == null) {
            throw new ResultException(ResultStatus.PARAM_ERR);
        }
        OrderProductFinalize orderProductFinalize = new OrderProductFinalize();
        orderProductFinalize.setId(id);
        orderProductFinalize.setState(1);
        orderService.setOrderProductFinalize(orderProductFinalize);
        return Result.success(null);
    }


    @RequestMapping(value = "out_list", method = RequestMethod.GET)
    @RequiresAuthentication
    public Object query_product_list(@Valid OrderProductOut form, BindingResult bindingResult, Page<OrderProductOut> page) {
        return orderService.findOrderProductOutPageList(page, form);
    }

    @RequestMapping(value = "out_edit", method = RequestMethod.GET)
    @RequiresAuthentication
    public Object product_out_edit(Long id) {
        return orderService.getOrderProductOut(id);
    }

    @RequiresAuthentication
    @PostMapping(value = "/out_edit")
    @RequiresRoles(value = {Constant.POSITION_DIRECTOR})
    public Object product_out_edit(@Valid OrderProductOut form) {
        Staff loginUser = getLoginUser();
        if (form.getId() == null) {
            form.setCreator(loginUser.getId());
        }
        orderService.setOrderProductOut(form);
        return Result.success(null);
    }

    @RequiresAuthentication
    @PostMapping(value = "/out_delete")
    public Object product_out_delete(Long id) {
        if (id == null) {
            throw new ResultException(ResultStatus.PARAM_ERR);
        }
        OrderProductOut orderProductOut = new OrderProductOut();
        orderProductOut.setId(id);
        orderProductOut.setState(1);
        orderService.setOrderProductOut(orderProductOut);
        return Result.success(null);
    }


    @RequestMapping(value = "out_work_list", method = RequestMethod.GET)
    @RequiresAuthentication
    public Object query_out_work_list(@Valid OrderProductOutWork form, BindingResult bindingResult, Page<OrderProductOutWork> page) {
        return orderService.findOrderProductOutWorkPageList(page, form);
    }

    @RequestMapping(value = "out_work_edit", method = RequestMethod.GET)
    @RequiresAuthentication
    public Object out_work_edit(Long id) {
        return orderService.getOrderProductOutWork(id);
    }

    @RequiresAuthentication
    @PostMapping(value = "/out_work_edit")
    @RequiresRoles(value = {Constant.POSITION_OUT, Constant.POSITION_DIRECTOR}, logical = Logical.OR)
    public Object out_work_edit(OrderProductOutWork form) {
        Staff loginUser = getLoginUser();
        if (form.getId() == null) {
            form.setCreator(loginUser.getId());
        }
        orderService.setOrderProductOutWork(form);
        return Result.success(null);
    }

    @RequiresAuthentication
    @PostMapping(value = "/out_work_delete")
    @RequiresRoles(value = {Constant.POSITION_OUT, Constant.POSITION_DIRECTOR}, logical = Logical.OR)
    public Object out_work_delete(Long id) {
        if (id == null) {
            throw new ResultException(ResultStatus.PARAM_ERR);
        }
        OrderProductOutWork orderProductOutWork = new OrderProductOutWork();
        orderProductOutWork.setId(id);
        orderProductOutWork.setState(1);
        orderService.setOrderProductOutWork(orderProductOutWork);
        return Result.success(null);
    }


    @GetMapping(value = "/material")
    public Object materials(Page<OrderProductMaterial> page, OrderMaterialQuery from) {
        if (from.getOrderId() == null && from.getProductId() == null) {
            throw new ResultException(ResultStatus.PARAM_ERR);
        }
        return orderService.findOrderProductMaterialPageList(page, from);
    }

    @GetMapping(value = "/material/statistics")
    public Object materialStatistics(Page<OrderMaterialSumDTO> page, OrderMaterialQuery from) {
        if (from.getOrderId() == null && from.getProductId() == null) {
            throw new ResultException(ResultStatus.PARAM_ERR);
        }
        return orderService.statisticsOrderMaterialPageList(page, from);
    }

    @PostMapping(value = "/product/material")
    @RequiresAuthentication
    @RequiresRoles(value = {Constant.POSITION_SALESMAN, Constant.POSITION_BOSS, Constant.POSITION_DIRECTOR}, logical = Logical.OR)
    public Result setMaterial(OrderProductMaterial orderMaterial) {
        if (orderMaterial.getId() == null) {
            orderMaterial.setCreator(getLoginUser().getId());
        }
        orderService.insertOrUpdateMaterial(orderMaterial);

        return Result.success(null);
    }

    @GetMapping(value = "/material_log")
    public Object materials_log(Page<OrderProductMaterialLog> page, OrderMaterialQuery from) {
        return orderService.findOrderMaterialLogPageList(page, from);
    }

    @RequiresAuthentication
    @PostMapping(value = "/product/material_log")
    @RequiresRoles(value = {Constant.POSITION_SALESMAN, Constant.POSITION_BOSS, Constant.POSITION_DIRECTOR}, logical = Logical.OR)
    public Result setMaterial_log(OrderProductMaterialLog orderMaterialLog) {
        if (orderMaterialLog.getId() == null) {
            orderMaterialLog.setCreator(getLoginUser().getId());
        }
        orderService.insertOrUpdateMaterialLog(orderMaterialLog);
        return Result.success(null);
    }

    @RequiresAuthentication
    @GetMapping(value = "/material/lack")
    @RequiresRoles(value = {Constant.POSITION_SALESMAN, Constant.POSITION_BOSS, Constant.POSITION_DIRECTOR}, logical = Logical.OR)
    public Object selectMaterialLack(Page<OrderMaterialLackDTO> page, Long materialId) {
        if (materialId == null) {
            throw new ResultException(ResultStatus.PARAM_ERR);
        }
        return orderService.selectOrderMaterialLackDTOPageList(page, materialId);
    }

    @RequiresAuthentication
    @GetMapping(value = "/export")
    @RequiresRoles(value = {Constant.POSITION_SALESMAN, Constant.POSITION_BOSS, Constant.POSITION_DIRECTOR}, logical = Logical.OR)
    public void export(HttpServletResponse response, OrderQuery form) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("orderNo", "订单号");
        map.put("customer", "客户");
        map.put("salesman", "销售员");
        map.put("device", "机型");
        map.put("sewingHead", "缝头");
        map.put("itemNum", "款号");
        map.put("mainYarn", "主纱");
        map.put("liningYarn", "里纱");
        map.put("size", "尺寸");
        map.put("targetNum", "目标总数");
        map.put("completedNum", "完成总数");
        map.put("finalizeNum", "定型数");
        map.put("outTargetNum", "外发目标总数");
        map.put("outCompletedNum", "外发完成数");
        map.put("produceState", "生产状态");
        map.put("finalizeState", "定型状态");

        List<ExportOrderDTO> exportOrderDTOS = orderService.selectExportData(form);

        String fileName = "订单";
        ServletOutputStream outputStream = null;
        try {
            response.setHeader("Content-disposition", "inline; filename = " + URLEncoder.encode(fileName, "utf-8") + ".xls");
            outputStream = response.getOutputStream();
            ExcelUtil.exportExcel(map, exportOrderDTOS, outputStream);
        } catch (IOException e) {
            log.error("", e);
        }
    }


}