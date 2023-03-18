package com.example.hibernate.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "students")
@Data
@NamedQueries({
        @NamedQuery(name = "countAll", query = "select count(s) from Student s"),
        @NamedQuery(name = "findByName", query = "from Student s where s.name = :name"),
        @NamedQuery(name = "deleteById", query = "delete from Student s where s.id = :id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "mark")
    private Integer mark;

    public Student(String name, Integer mark) {
        this.name = name;
        this.mark = mark;
    }
}