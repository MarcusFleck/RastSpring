package com.demorest.tools;

import com.demorest.entity.Artist;
import com.demorest.entity.ArtistProto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by marcus on 13/12/2016.
 */
public class ProtoBinder {
    public static ArtistProto.ArtistList bindFromList(List<Artist> list) {
        return ArtistProto.ArtistList.newBuilder()
                .addAllArtist(
                        list.stream().map(artist -> ArtistProto.Artist.newBuilder()
                                .setId(artist.getId())
                                .setFirstName(artist.getFirstName())
                                .setLastName(artist.getLastName())
                                .setRate(artist.getRate())
                                .setDate(Objects.toString(artist.getDate(), ""))
                                .build()).collect(Collectors.toList())
                ).build();
    }

    public static ArtistProto.Artist bindFromObject(Artist artist) {
        return ArtistProto.Artist.newBuilder()
                .setId(artist.getId())
                .setFirstName(artist.getFirstName())
                .setLastName(artist.getLastName())
                .setRate(artist.getRate())
                .setDate(Objects.toString(artist.getDate(), ""))
                .build();
    }

    public static Artist bindFromProto(ArtistProto.Artist artist) {
        return new Artist(artist.getId(),
                artist.getFirstName(),
                artist.getLastName(),
                artist.getRate(),
                parseDate(artist.getDate()));
    }

    public static List<Artist> bindFromProtoList(List<ArtistProto.Artist> list) {
        List<Artist> temp = list.stream().map(artist -> new Artist(
                        artist.getId(),
                        artist.getFirstName(),
                        artist.getLastName(),
                        artist.getRate(),
                        parseDate(artist.getDate())
                )
        ).collect(Collectors.toList());
        return temp;
    }

    //protobuf object has no optinal for date value
    private static LocalDateTime parseDate(String date) {
        if (date != null) {
            return LocalDateTime.parse(date);
        } else {
            return null;
        }
    }
}
