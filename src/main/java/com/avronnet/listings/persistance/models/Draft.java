package com.avronnet.listings.persistance.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Draft")
public class Draft implements Serializable {
    private String id;
    private String title;
    private String description;
}
