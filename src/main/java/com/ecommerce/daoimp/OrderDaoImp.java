package com.ecommerce.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.config.DBconfig;
import com.ecommerce.constants.OrderQueries;
import com.ecommerce.constants.OrderStatus;
import com.ecommerce.constants.PaymentStatus;
import com.ecommerce.dao.OrderDao;
import com.ecommerce.model.Order;

public class OrderDaoImp implements OrderDao
{

	private Connection connection;

	public OrderDaoImp()
	{
		connection = DBconfig.getConnection();
	}

	@Override
	public long createOrder(Order order)
	{
		String query = OrderQueries.INSERT_ORDER;

		try
		{
			PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setLong(1, order.getUserId());
			ps.setDouble(2, order.getTotalAmount());
			ps.setString(3, order.getStatus().name());
			ps.setString(4, order.getPaymentStatus().name());

			int rows = ps.executeUpdate();

			if (rows > 0)
			{
				ResultSet rs = ps.getGeneratedKeys();

				if (rs.next())
				{
					return rs.getLong(1);
				}
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	public Order getOrderById(long orderId)
	{
		String query = OrderQueries.GET_ORDER_BY_ID;

		try
		{
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setLong(1, orderId);

			ResultSet rs = ps.executeQuery();

			if (rs.next())
			{
				Order order = new Order();

				order.setOrderId(rs.getLong("order_id"));

				order.setUserId(rs.getLong("user_id"));

				order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());

				order.setTotalAmount(rs.getDouble("total_amount"));

				order.setStatus(OrderStatus.valueOf(rs.getString("status")));

				order.setPaymentStatus(PaymentStatus.valueOf(rs.getString("payment_status")));

				order.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

				order.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());

				return order;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Order> getOrdersByUserId(long userId)
	{
		List<Order> orders = new ArrayList<>();

		String query = OrderQueries.GET_ORDER_BY_USER_ID;

		try
		{
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setLong(1, userId);

			ResultSet rs = ps.executeQuery();

			while (rs.next())
			{
				Order order = new Order();

				order.setOrderId(rs.getLong("order_id"));
				order.setUserId(rs.getLong("user_id"));
				order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
				order.setTotalAmount(rs.getDouble("total_amount"));
				order.setStatus(OrderStatus.valueOf(rs.getString("status")));
				order.setPaymentStatus(PaymentStatus.valueOf(rs.getString("payment_status")));
				order.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
				order.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());

				orders.add(order);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return orders;
	}

	@Override
	public boolean updateOrderStatus(long orderId, OrderStatus status)
	{
		String query = OrderQueries.UPDATE_ORDER_STATUS;

		try
		{
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setString(1, status.name());

			ps.setLong(2, orderId);

			int rowsUpdated = ps.executeUpdate();

			return rowsUpdated > 0;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean cancelOrder(long orderId, long userId)
	{
		String query = OrderQueries.CANCEL_ORDER;

		try
		{
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setString(1, OrderStatus.CANCELLED.name());
			ps.setLong(2, orderId);
			ps.setLong(3, userId);

			int rowsUpdated = ps.executeUpdate();

			return rowsUpdated > 0;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return false;
	}
}