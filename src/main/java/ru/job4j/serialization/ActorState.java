package ru.job4j.serialization;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlRootElement(name = "state")
@XmlType(propOrder = {
        "id", "actorId", "roleId", "professionId", "disciplineId",
        "classId", "fatherId", "motherId", "childId", "name"
})
public class ActorState {

    private Long id;
    private Long actorId;
    private Integer roleId;
    private Integer professionId;
    private Integer disciplineId;
    private Long classId;
    private Long fatherId;
    private Long motherId;
    private Long childId;
    private String name;

    public ActorState() { }

    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        id = value;
    }

    public Long getActorId() {
        return actorId;
    }

    public void setActorId(Long value) {
        actorId = value;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer value) {
        roleId = value;
    }

    public Integer getProfessionId() {
        return professionId;
    }

    public void setProfessionId(Integer professionId) {
        this.professionId = professionId;
    }

    public Integer getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(Integer disciplineId) {
        this.disciplineId = disciplineId;
    }

    public Long getFatherId() {
        return fatherId;
    }

    public void setFatherId(Long fatherId) {
        this.fatherId = fatherId;
    }

    public Long getMotherId() {
        return motherId;
    }

    public void setMotherId(Long motherId) {
        this.motherId = motherId;
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ActorState that = (ActorState) o;
        return
                Objects.equals(id, that.id)
                && Objects.equals(actorId, that.actorId)
                && Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, actorId, roleId);
    }
}