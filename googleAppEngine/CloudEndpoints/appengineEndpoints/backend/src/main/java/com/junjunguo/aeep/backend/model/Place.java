package com.junjunguo.aeep.backend.model;

import com.google.appengine.api.datastore.GeoPt;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;


/**
 * Place entity.
 */
@Entity
public class Place {

    /**
     * * Unique identifier of this Entity in the database.
     */
    @Id
    private Long id;

    /**
     * The name of the place.
     */
    private String name;

    /**
     * The address of the place.
     */
    private String address;

    /**
     * The GPS coordinates of the place, as a GeoPt.
     */
    private GeoPt location;

    /**
     * Returns the unique identifier of this place.
     * @return the unique identifier associated to this place in the database.
     */
    public final Long getPlaceId() {
        return id;
    }

    /**
     * Sets the unique identifier of this place.
     * @param pPlaceId the identifier to set for this place.
     */
    public final void setPlaceId(final Long pPlaceId) {
        this.id = pPlaceId;
    }

    /**
     * Returns the name of the place.
     * @return the name of the place.
     */
    public final String getName() {
        return name;
    }

    /**
     * Sets the name of the place.
     * @param pName the name to set for this place.
     */
    public final void setName(final String pName) {
        this.name = pName;
    }

    /**
     * Returns the address of the place.
     * @return the address of the place.
     */
    public final String getAddress() {
        return address;
    }

    /**
     * Sets the address of the place.
     * @param pAddress the name to set for this address.
     */
    public final void setAddress(final String pAddress) {
        this.address = pAddress;
    }

    /**
     * Returns the location of the place.
     * @return the GPS location of this place, as a GeoPt.
     */
    public final GeoPt getLocation() {
        return location;
    }

    /**
     * Sets the location of the place.
     * @param pLocation the GPS location to set for this place, as a GeoPt.
     */
    public final void setLocation(final GeoPt pLocation) {
        this.location = pLocation;
    }
}
