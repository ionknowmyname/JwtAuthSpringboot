package com.ionknowmyname.userauth.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
//@Document(collection = "roles")
public class Role {

    @Id
    private String id;
    // @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)

    private String role;


}
