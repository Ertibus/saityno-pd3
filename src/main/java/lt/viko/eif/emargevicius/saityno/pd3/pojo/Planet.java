package lt.viko.eif.emargevicius.saityno.pd3.pojo;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Represents Planet object used by
 * {@link Planets} which has a list of
 * attributes: name, god, godAspect, orbitTimeDays, orbitalSpeed,
 * escapeVelocity, gravity, surfacePressure, surfaceArea, satellites,
 * temperature.
 *
 *
 * @author Emil
 * @version 1.0-SNAPSHOT
 * @since 1.0-SNAPSHOT
 * @see Planets
 */
@XmlType(propOrder = {
    "id",
    "name",
    "god",
    "godAspect",
    "orbitTime",
    "orbitalSpeed",
    "escapeVelocity",
    "gravity",
    "surfaceArea",
    "satellites",
    "temperature"
})
@Schema(title = "Planet", description = "Planet object with many properties")
public class Planet {
    @Schema(title = "ID", description = "ID of the planet")
    private int id;

    @Schema(title = "Name", description = "Name of the planet")
    private String name;

    @Schema(title = "God", description = "God the planet is named after")
    private String god;

    @Schema(title = "Gods Aspect", description =  "Aspect the God of the planet represent")
    private String godAspect;

    @Schema(title = "Orbit Time", description = "Orbit Time of the planet")
    private OrbitTime orbitTime;

    @Schema(title = "Orbital Speed", description = "Orbit Speed of the planet")
    private float orbitalSpeed;

    @Schema(title = "Escape Velocity", description = "Escape velocity of the planet")
    private float escapeVelocity;

    @Schema(title = "Gravity", description = "Gravity of the planet")
    private float gravity;

    @Schema(title = "Surface Area", description = "Surface area of the planet")
    private SurfaceArea surfaceArea;

    @Schema(title = "Satellites", description = "Satellites count of the planet")
    private int satellites;

    @Schema(title = "Temperature", description = "Temperature of the planets surface")
    private float temperature;

    /**
     * Getter for id of the planet.
     *
     * @return the id of the planet.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for id of the planet.
     *
     * @param id id of the planet.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for name of the planet.
     *
     * @return the name of the planet.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name of the planet.
     *
     * @param name name of the planet.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the name of the god associated with the planet.
     *
     * @return gods name.
     */
    public String getGod() {
        return god;
    }

    /**
     * Setter for the name of the god associated with the planet.
     *
     * @param god gods name.
     */
    public void setGod(String god) {
        this.god = god;
    }

    /**
     * Getter for the aspect of the god.
     *
     * @return gods aspect.
     */
    public String getGodAspect() {
        return godAspect;
    }

    /**
     * Setter for the aspect of the god.
     *
     * @param godAspect gods aspect.
     */
    @XmlElement(name = "god-aspect")
    public void setGodAspect(String godAspect) {
        this.godAspect = godAspect;
    }

    /**
     * Getter for the time it takes for the planet to orbit around another
     * object.
     *
     * @return the {@link OrbitTime}
     * object.
     * @see OrbitTime
     */
    public OrbitTime getOrbitTime() {
        return orbitTime;
    }

    /**
     * Setter for the time it takes for the planet to orbit around another
     * object.
     *
     * @param orbitTime the
     * {@link OrbitTime} object.
     * @see OrbitTime
     */
    @XmlElement(name = "orbit-time")
    public void setOrbitTime(OrbitTime orbitTime) {
        this.orbitTime = orbitTime;
    }

    /**
     * Getter for the speed the planet orbits. Measured in km/s.
     *
     * @return orbit speed in km/s.
     */
    public float getOrbitalSpeed() {
        return orbitalSpeed;
    }

    /**
     * Setter for the speed the planet orbits. Measured in km/s.
     *
     * @param orbitalSpeed orbit speed in km/s.
     */
    @XmlElement(name = "orbital-speed")
    public void setOrbitalSpeed(float orbitalSpeed) {
        this.orbitalSpeed = orbitalSpeed;
    }

    /**
     * Getter for minimum speed needed to escape from the gravitational
     * influence of the sun. Measured in km/s.
     *
     * @return escape velocity in km/s.
     */
    public float getEscapeVelocity() {
        return escapeVelocity;
    }

    /**
     * Setter for minimum speed needed to escape from the gravitational
     * influence of the sun. Measured in km/s.
     *
     * @param escapeVelocity escape velocity in km/s.
     */
    @XmlElement(name = "escape-velocity")
    public void setEscapeVelocity(float escapeVelocity) {
        this.escapeVelocity = escapeVelocity;
    }

    /**
     * Getter for gravity at the planets surface (m/s^2).
     *
     * @return the gravity in m/s^2.
     */
    public float getGravity() {
        return gravity;
    }

    /**
     * Setter for gravity at the planets surface (m/s^2).
     *
     * @param gravity the gravity in m/s^2.
     */
    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    /**
     * Getter for the surface area object.
     *
     * @return the {@link SurfaceArea} object
     * @see SurfaceArea
     */
    public SurfaceArea getSurfaceArea() {
        return surfaceArea;
    }

    /**
     * Setter for the {@link SurfaceArea} object.
     *
     * @param surfaceArea the {@link SurfaceArea} object.
     * @see SurfaceArea
     */
    @XmlElement(name = "surface-area")
    public void setSurfaceArea(SurfaceArea surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    /**
     * Getter for the natural satellite count.
     *
     * @return natural satellite count.
     */
    public int getSatellites() {
        return satellites;
    }

    /**
     * Setter for the natural satellite count.
     *
     * @param satellites natural satellite count.
     */
    public void setSatellites(int satellites) {
        this.satellites = satellites;
    }

    /**
     * Getter for the temperature.
     *
     * @return a float representing the temperature of the planet
     */
    public float getTemperature() {
        return temperature;
    }

    /**
     * Setter for the temperature
     *
     * @param temperature a float representing the temperature of the planet
     */
    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    /**
     * Override for toString() method from {@link java.lang.Object}
     *
     * @return a string representation of the object.
     * @see java.lang.Object
     */
    @Override
    public String toString() {
        return String.format("\tPlanet:\n"
                + "\t\tid:\t%s\n"
                + "\t\tname:\t%s\n"
                + "\t\tgod:\t%s\n"
                + "\t\tgod aspect:\t%s\n"
                + "%s\n"
                + "\t\torbital speed:\t\t%f\n"
                + "\t\tescape velocity:\t%f\n"
                + "\t\tgravity:\t\t%f\n"
                + "\t\tsurface pressure:\t%s\n"
                + "%s\n"
                + "\t\tsatellites:\t%d\n"
                + "\t\ttemperature:\t%f\n", id, name, god, godAspect, orbitTime, orbitalSpeed, escapeVelocity, gravity, surfaceArea, satellites, temperature);
    }
}
