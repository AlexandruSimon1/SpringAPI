package utils;

import dto.MenuDTO;
import dto.OrderDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import model.Menu;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.OrderService;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main/v1/orders")
@Api(value = "orders", description = "CRUD Operations for Orders", tags = "ORDERS")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "GET ALL ORDERS", notes = "\n" + "This operation gets all orders")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "GET ORDER BY ID", notes = "\n" + "This operation get order by id")
    public OrderDTO getOrderById(@PathVariable int orderId) {
        return orderService.getOrderById(orderId);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "DELETE ORDER BY ID", notes = "\n" + "This operation deletes order by id")
    public OrderDTO deleteOrderById(@PathVariable int orderId) {
        return orderService.deleteOrderById(orderId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "CREATE ORDER", notes = "\n" + "This operation creates order")
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }

    @PutMapping("/{orderId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "UPDATE ORDER BY ID", notes = "\n" + "This operation updates an existing order")
    public OrderDTO updateOrderById(@PathVariable int orderId, @RequestBody OrderDTO orderDTO) {
        return orderService.update(orderDTO, orderId);
    }

    @GetMapping("{/orderId}/menu")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "GET ALL PRODUCT BY ORDER ID", notes = "\n" + "This operation gets all products from order id")
    public Set<MenuDTO> getAllProductByOrderId(@PathVariable int orderId) {
        return orderService.findAllProductByOrderId(orderId);
    }

    @PutMapping("/{orderId}/menu")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "UPDATE PRODUCT BY ORDER ID", notes = "\n" + "This operation updates products from existing order by order id")
    public MenuDTO updateProductByOrderId(@PathVariable int orderId, @PathVariable int removed_productId, @RequestBody MenuDTO menuDTO) {
        return orderService.updateProductByOrderId(orderId, removed_productId, menuDTO);
    }

    @DeleteMapping("/{orderId}/menu")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "DELETE PRODUCT BY ORDER ID", notes = "\n" + "This operation deletes products from existing order by order id")
    public MenuDTO deleteProductByOrderId(@PathVariable int orderId, @PathVariable int removedOrderId) {
        return orderService.deleteProductByOrderId(orderId, removedOrderId);
    }
}
