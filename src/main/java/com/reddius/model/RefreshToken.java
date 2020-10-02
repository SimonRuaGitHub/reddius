package com.reddius.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "refreshtoken")
public class RefreshToken {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	    private String token;
	    private Instant createdDate;
}
