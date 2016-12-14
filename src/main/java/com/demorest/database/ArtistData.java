package com.demorest.database;

import com.demorest.entity.Artist;
import com.demorest.tools.LocalDateTimeHandler;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by marcus on 10/11/2016.
 */
public interface ArtistData {
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "rate", column = "rate"),
            @Result(property = "date", column = "date", typeHandler = LocalDateTimeHandler.class)
    })

    @Select("Select * from artist order by rate DESC")
    public List<Artist> select();

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "rate", column = "rate"),
            @Result(property = "date", column = "date", typeHandler = LocalDateTimeHandler.class)
    })
    @Select("Select * from artist where id = #{id}")
    public Artist selectById(@Param("id") int id);

    @Insert("INSERT INTO artist (first_name,last_name,rate,date) VALUES (#{firstName},#{lastName},#{rate},#{date, typeHandler = com.demorest.tools.LocalDateTimeHandler})")
    public void insert(Artist artist);

    @Update("UPDATE artist SET " +
            "first_name = #{firstName}, " +
            "last_name = #{lastName}, " +
            "rate = #{rate}, " +
            "date = #{date, typeHandler = com.demorest.tools.LocalDateTimeHandler} " +
            "WHERE id = #{id}")
    public void update(Artist artist);

    @Delete("DELETE FROM artist WHERE id = #{id}")
    public void delete(@Param("id") int id);
}
