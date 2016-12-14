package com.demorest.endpoint;

import com.demorest.connection.MyBatisConnectionFactory;
import com.demorest.database.ArtistData;
import com.demorest.entity.ArtistProto;
import com.demorest.entity.ArtistProto.*;
import com.demorest.tools.ProtoBinder;
import com.pakulov.jersey.protobuf.internal.MediaTypeExt;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Created by marcus on 09/11/2016.
 */

@Path("/proto")
public class ArtistEndpointProto {
    @Context
    UriInfo uriInfo;

    @Autowired
    MyBatisConnectionFactory myBatisConnectionFactory;

    @GET
    @Produces(MediaTypeExt.APPLICATION_PROTOBUF)
    public ArtistList getArtist() throws Exception {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        ArtistData mapper = sqlSession.getMapper(ArtistData.class);
        return ProtoBinder.bindFromList(mapper.select());
    }

    @GET
    @Path("/{id}")
    @Produces(MediaTypeExt.APPLICATION_PROTOBUF)
    public Artist getArtistById(@PathParam("id") int id) throws Exception {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        ArtistData mapper = sqlSession.getMapper(ArtistData.class);
        return ProtoBinder.bindFromObject(mapper.selectById(id));
    }

    @POST
    @Consumes(MediaTypeExt.APPLICATION_PROTOBUF)
    @Produces(MediaTypeExt.APPLICATION_PROTOBUF)
    public void setArtist(Artist artist) throws Exception {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        ArtistData mapper = sqlSession.getMapper(ArtistData.class);
        mapper.insert(ProtoBinder.bindFromProto(artist));
    }

    @PUT
    @Consumes(MediaTypeExt.APPLICATION_PROTOBUF)
    @Produces(MediaTypeExt.APPLICATION_PROTOBUF)
    public void updateArtist(Artist artist) throws Exception {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        ArtistData mapper = sqlSession.getMapper(ArtistData.class);
        mapper.update(ProtoBinder.bindFromProto(artist));
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaTypeExt.APPLICATION_PROTOBUF)
    public void deleteArtist(@PathParam("id") int id) throws Exception {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        ArtistData mapper = sqlSession.getMapper(ArtistData.class);
        mapper.delete(id);
    }
}