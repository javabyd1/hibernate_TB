package com.sdajava.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class BooksEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private Date published;
    @Column
    private String isbn;
    @Column
    private String category;
    @Column(name = "page_count")
    private Integer pageCount;
    @Column
    private String publisher;
    @Column
    private BigDecimal price;
    @Column(name = "on_stock")
    private Integer onStock;
}
