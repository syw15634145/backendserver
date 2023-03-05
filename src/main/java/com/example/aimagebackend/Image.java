package com.example.aimagebackend;

import jakarta.persistence.*;
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name = "fileName")
    private String fileName;
    @Column(name = "status")
    private String status;
    @Column(name = "email")
    private String email;
    @Lob
    @Column(name = "data", columnDefinition = "longblob")
    private byte[] data;

    public Image() {
    }

    public Image(String f, String s, String e, byte[] d) {
        this.fileName = f;
        this.status = s;
        this.email = e;
        this.data = d;
    }

    // 省略构造方法、Getter 和 Setter 方法
}

