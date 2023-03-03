package ra.student.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studentId")
    private int studentId;
    @Column(name = "studentName",columnDefinition = "nvachar(50)")
    private String studentName;
    @Column(name = "studentAge")
    private int studentAge;
    @Column(name = "studentStatus")
    private boolean studentStatus;
}
