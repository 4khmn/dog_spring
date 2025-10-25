package com.example.demo.DogRepository;

import com.example.demo.DogRepository.POJO.DogComment;
import com.example.demo.DogRepository.POJO.DogInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DogRepository {

    private final JdbcTemplate jdbc;

    public DogRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    public boolean isThisDogExistInTable(String url) {
        String sql = "SELECT COUNT(*) FROM dogs WHERE url = ?";
        Integer count = jdbc.queryForObject(sql, new Object[]{url}, Integer.class);
        return count != null && count > 0;
    }

    public void storeInfoAboutDog(DogInfo dogInfo){
    String sql = "INSERT INTO dogs(url, liked) VALUES (?, ?)";

    jdbc.update(sql, dogInfo.getUrl(), dogInfo.isLiked());
    }

    public void updateLike(String dogUrl, boolean liked){
        String sql = "UPDATE dogs SET liked = ? WHERE url = ?";
        jdbc.update(sql, liked, dogUrl);
    }

    public List<DogInfo> getAllInfoAboutDogs(){
        String sql = "SELECT * FROM dogs";
        RowMapper<DogInfo> DogInfoRowMapper = (r, i)->{
            DogInfo dogInfo = new DogInfo();
            dogInfo.setId(r.getInt(1));
            dogInfo.setUrl(r.getString(2));
            dogInfo.setLiked(r.getBoolean(3));
            return dogInfo;
        };
        return jdbc.query(sql, DogInfoRowMapper);
    }

    public int getCountOfRecords(){
        String sql = "SELECT COUNT(*) FROM dogs";
        return jdbc.queryForObject(sql, Integer.class);
    }

    public String getRandomIngFromTable(){
        String sql = "SELECT url FROM dogs ORDER BY RANDOM() LIMIT 1";
        return jdbc.queryForObject(sql, String.class);
    }

    public DogInfo getDogByUrl(String url) {
        String sql = "SELECT * FROM dogs WHERE url = ?";

        return jdbc.queryForObject(sql, new Object[]{url}, (rs, rowNum) -> {
            DogInfo dogInfo = new DogInfo();
            dogInfo.setId(rs.getInt("id"));
            dogInfo.setUrl(rs.getString("url"));
            dogInfo.setLiked(rs.getBoolean("liked"));
            return dogInfo;
        });
    }


    public void storeInfoAboutDogComments(DogComment dogComment){
        String sql = "INSERT INTO dog_comments(dog_id, comment) VALUES (?, ?)";

        jdbc.update(sql, dogComment.getDog_id(), dogComment.getComment());
    }

    public List<DogComment> getAllInfoAboutDogsComments(){
        String sql = "SELECT * FROM dog_comments";
        RowMapper<DogComment> DogInfoRowMapper = (r, i)->{
            DogComment dogComment = new DogComment();
            dogComment.setId(r.getInt(1));
            dogComment.setDog_id(r.getInt(2));
            dogComment.setComment(r.getString(3));
            return dogComment;
        };
        return jdbc.query(sql, DogInfoRowMapper);
    }
    public List<DogComment> getAllCommentsForCurrentDog(String url){
        String sql =
                "SELECT * FROM dog_comments " +
                "JOIN dogs " +
                "ON dogs.id = dog_comments.dog_id " +
                "WHERE dogs.url = ?";

        RowMapper<DogComment> DogCommentsRowMapper = (r, i)->{
            DogComment dogComment = new DogComment();
            dogComment.setId(r.getInt(1));
            dogComment.setDog_id(r.getInt(2));
            dogComment.setComment(r.getString(3));
            return dogComment;
        };
        return jdbc.query(sql, DogCommentsRowMapper, url);
    }



}
