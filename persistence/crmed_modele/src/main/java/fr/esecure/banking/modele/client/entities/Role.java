/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.esecure.banking.modele.client.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author sniang
 */
@Entity
@Table(name="role", schema="esecure")
public class Role extends AbstractDateEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_ROLE")
    private Long idRole;
    @Column(name="ROLE_DESC")
    private String roleDesc;
    @Column(name="ROLE_NAME")
    private String nameRole;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role()
    {
        super();
    }

    public Role(String nameRole) {
        this.nameRole = nameRole;
        this.roleDesc=nameRole;
    }

    /**
     * @return the idRole
     */
    public Long getIdRole() {
        return idRole;
    }

    /**
     * @param idRole the idRole to set
     */
    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    /**
     * @return the roleDesc
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     * @param roleDesc the roleDesc to set
     */
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    /**
     * @return the nameRole
     */
    public String getNameRole() {
        return nameRole;
    }

    /**
     * @param nameRole the nameRole to set
     */
    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    /**
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }
}
