package com.junjunguo.tsag.model;

import javax.persistence.*;

/**
 * Created by GuoJunjun <junjunguo.com> on 16/10/15.
 */
@Entity
@Table(name = "TAG")
public class Tag {
    /**
     * TAG ID
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private int    id;
    @Column(name = "LABEL",
            nullable = false,
            columnDefinition = "VARCHAR(255)",
            unique = true)
    private String label;
    //    @ManyToMany(mappedBy = "tags",
    //                fetch = FetchType.LAZY,
    //                targetEntity = User.class
    //    )
    //    @JsonIgnore
    //    private List<UT> users = new ArrayList<UT>();

    public Tag(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public Tag(String label) {
        this.label = label;
    }

    public Tag() {

    }

    //    public void addUser(UT user) {
    //        users.add(user);
    //    }
    //
    //    public void removeUser(UT user) {
    //        users.remove(user);
    //    }
    //
    //    public List<UT> getUsers() {
    //        return users;
    //    }
    //
    //    public void setUsers(List<UT> users) {
    //        this.users = users;
    //    }

    /**
     * Gets TAG ID.
     *
     * @return Value of TAG ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets label.
     *
     * @return Value of label.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets new TAG ID.
     *
     * @param id New value of TAG ID.
     */
    void setId(int id) {
        this.id = id;
    }

    /**
     * Sets new label.
     *
     * @param label New value of label.
     */
    void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Tag [id=" + id +
               ", label='" + label +
               ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;

        Tag tag = (Tag) o;

        if (getId() != tag.getId()) return false;
        return getLabel().equals(tag.getLabel());

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getLabel().hashCode();
        return result;
    }
}
