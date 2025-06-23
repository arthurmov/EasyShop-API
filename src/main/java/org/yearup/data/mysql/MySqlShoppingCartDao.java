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
    public List<ShoppingCartItem> getAllItems() {

        List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();

//        String query = """
//                SELECT *
//                FROM shopping_cart""";
//
//        try(Connection connection = getConnection();
//            PreparedStatement ps = connection.prepareStatement(query);
//            ResultSet resultSet = ps.executeQuery()
//        ) {
//
//            while(resultSet.next()) {
//                int userId = resultSet.getInt(1);
//                int productId = resultSet.getInt(2);
//                int quantity = resultSet.getInt(3);
//                ShoppingCartItem sci = new ShoppingCartItem();
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        return shoppingCartItems;
    }

    @Override
    public ShoppingCart getByUserId(int userId) {
        return null;
    }

    @Override
    public ShoppingCartItem create(Product product) {
        return null;
    }

    @Override
    public void update(int userId, Product product) {

    }

    @Override
    public void delete(int userId) {

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
