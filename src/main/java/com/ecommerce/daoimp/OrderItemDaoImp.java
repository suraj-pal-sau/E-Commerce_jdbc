package com.ecommerce.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.config.DBconfig;
import com.ecommerce.constants.OrderItemQueries;
import com.ecommerce.dao.OrderItemDao;
import com.ecommerce.model.OrderItem;



public class OrderItemDaoImp implements OrderItemDao
{

    private Connection connection;

    public OrderItemDaoImp()
    {
        connection = DBconfig.getConnection();
    }

    @Override
    public boolean addOrderItems(List<OrderItem> orderItems)
    {
        String query = OrderItemQueries.ADD_ORDER_ITEM;

        try
        {
            PreparedStatement ps = connection.prepareStatement(query);

            for(OrderItem item : orderItems)
            {
                ps.setLong(1, item.getOrderId());
                ps.setLong(2, item.getProductId());
                ps.setInt(3, item.getQuantity());
                ps.setDouble(4, item.getPrice());
                ps.setDouble(5, item.getSubtotal());

                ps.addBatch();
            }

            ps.executeBatch();

            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(long orderId)
    {
        List<OrderItem> orderItems = new ArrayList<>();

        String query = OrderItemQueries.getOrderItemsByOrderId;

        try
        {
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setLong(1, orderId);

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                OrderItem item = new OrderItem();

                item.setOrderItemId(rs.getLong("order_item_id"));
                item.setOrderId(rs.getLong("order_id"));
                item.setProductId(rs.getLong("product_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                item.setSubtotal(rs.getDouble("subtotal"));

                item.setCreatedAt(
                        rs.getTimestamp("created_at")
                          .toLocalDateTime());

                orderItems.add(item);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return orderItems;
    }

    @Override
    public boolean deleteOrderItems(long orderId)
    {
        String query = OrderItemQueries.deleteOrderItems;

        try
        {
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setLong(1, orderId);

            int rowsDeleted = ps.executeUpdate();

            return rowsDeleted > 0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

}
