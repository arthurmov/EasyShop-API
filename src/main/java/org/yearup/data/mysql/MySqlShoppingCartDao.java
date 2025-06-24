package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Category;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {

    public MySqlShoppingCartDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public ShoppingCart getByUserId(int userId) {
        // get shopping cart by user id

        ShoppingCart shoppingCart = new ShoppingCart();

        String query = """
                SELECT
                p.*,
                sc.quantity,
                (p.price * sc.quantity) AS total
                FROM shopping_cart sc
                JOIN products p ON sc.product_id = p.product_id
                WHERE sc.user_id = ?;
                """;

        try(
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query);
        ) {
            ps.setInt(1, userId);
            try(ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()){

                    Product product = new Product();

                    product.setProductId(resultSet.getInt("product_id"));
                    product.setName(resultSet.getString("name"));
                    product.setPrice(resultSet.getBigDecimal("price"));
                    product.setCategoryId(resultSet.getInt("category_id"));
                    product.setDescription(resultSet.getString("description"));
                    product.setColor(resultSet.getString("color"));
                    product.setStock(resultSet.getInt("stock"));
                    product.setImageUrl(resultSet.getString("image_url"));
                    product.setFeatured(resultSet.getBoolean("featured"));
                    int quantity = resultSet.getInt("quantity");

                    ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
                    shoppingCartItem.setProduct(product);
                    shoppingCartItem.setQuantity(quantity);
                    shoppingCart.add(shoppingCartItem);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return shoppingCart;
    }

    @Override
    public ShoppingCartItem create(int userId, ShoppingCartItem shoppingCartItem) {

        String query = """
                insert into shopping_cart
                (user_id, product_id)
                values  (?, ?)
                """;

        try(
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query);
        )
        {
            int productId = shoppingCartItem.getProduct().getProductId();
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return shoppingCartItem;
}

    @Override
    public void update(int userId, ShoppingCartItem shoppingCartItem) {

        // to do
        String query = """
                update shopping_cart
                SET quantity = ?
                where user_id = ?
                AND product_id = ?
                """;
        try(
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query);
        )
        {

            int quantity = shoppingCartItem.getQuantity();
            int productId   = shoppingCartItem.getProductId();
            ps.setInt(1, quantity);
            ps.setInt(2, userId);
            ps.setInt(3, productId);
            ps.executeUpdate();
        }

        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int userId) {
        // delete shopping cart
        String query = """
                DELETE FROM shopping_cart
                WHERE user_id = ?""";

        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    // work in progress

//    private ShoppingCart mapRow(ResultSet row) throws SQLException
//    {
//        int userId = row.getInt("user_id");
//        int productId = row.getInt("product_id");
//        int quantity = row.getInt("quantity");
//
//        ShoppingCart shoppingCart = new ShoppingCart()
//        {{
//            setUserId(userId);
//            contains(productId);
//            setItems(quantity);
//        }};
//
//        return shoppingCart;
//    }
}
