package com.demorest.endpoint;

import com.demorest.connection.MyBatisConnectionFactory;
import com.demorest.database.ArtistData;
import com.demorest.entity.Artist;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Created by marcus on 09/11/2016.
 */

@Path("/artists")
@Api(value = "Artists",description = "Artists CRUD")
public class ArtistEndpoint {
    @Context
    UriInfo uriInfo;

    @Autowired
    MyBatisConnectionFactory myBatisConnectionFactory;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(value = "Consultar artistas, ordenado por Rate", response = Artist.class, responseContainer = "List")
    public List<Artist> getArtist() throws Exception {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        ArtistData mapper = sqlSession.getMapper(ArtistData.class);
        return mapper.selectAll();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(value = "Consultar artista pelo numero de ID", response = Artist.class, responseContainer = "Artist")
    public Artist getArtistById(@PathParam("id") int id) throws Exception {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        ArtistData mapper = sqlSession.getMapper(ArtistData.class);
        return mapper.selectById(id);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(value = "Cadastrar novo artista")
    public void setArtist(Artist artist) throws Exception {
        System.out.println(artist);
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        ArtistData mapper = sqlSession.getMapper(ArtistData.class);
        mapper.insert(artist);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(value = "Atualizar dados do artista")
    public void updateArtist(Artist artist) throws Exception {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        ArtistData mapper = sqlSession.getMapper(ArtistData.class);
        mapper.update(artist);
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(value = "Deletar artista por ID")
    public void deleteArtist(@PathParam("id") int id) throws Exception {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        ArtistData mapper = sqlSession.getMapper(ArtistData.class);
        mapper.delete(id);
    }
}
