package the_template;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BookDAO {

    @Autowired
    private JdbcOperations jdbcOperations;

    public List<Map<String, Object>> getToList() {
        List<Map<String, Object>> maps =
                jdbcOperations.queryForList("select * from t_book where id > 0");
        return maps;
    }

    public Map<String, Object> getToMap() {
        String sql = "select * from t_book where id > ?";

        Map<String, Object> ret = jdbcOperations.queryForMap(sql, 1);
        return ret;
    }

    public Book getToBook() {
        String sql = "select * from t_book where id > 1";

        Book book = jdbcOperations.queryForObject(
                sql,
                (rs, rowNum) -> new Book(rs.getInt(1), rs.getString(2), rs.getFloat(3))
        );
        return book;
    }

    public void create() {
        String sql = "insert into book (name, price) values (?, ?)";
        jdbcOperations.update(sql, "jQuery", 33);
    }

}
