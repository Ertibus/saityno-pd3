package lt.viko.eif.emargevicius.saityno.pd3.pojo;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.stream.Collectors;

/**
 * Represents XML root named Planets, which has a list of objects named
 * {@link Planet}.
 *
 *
 * @author Emil
 * @version 1.0-SNAPSHOT
 * @since 1.0-SNAPSHOT
 * @see Planet
 */
@XmlRootElement
public class Planets {

    private List<Planet> planets;

    /**
     * Getter for {@link Planet}
     * object list.
     *
     * @return a list of
     * {@link Planet} objects.
     * @see Planet
     */
    public List<Planet> getPlanets() {
        return planets;
    }

    /**
     * Setter for {@link Planet}
     * object list.
     *
     * @param planets a list of for
     * {@link Planet} objects.
     * @see Planet
     */
    @XmlElement(name = "planet")
    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }

    /**
     * Override for toString() method from {@link java.lang.Object}
     *
     * @return a string representation of the object.
     * @see java.lang.Object
     */
    @Override
    public String toString() {
        return String.format("Planets:\n"
                + "%s\n",
                planets.stream().map(Object::toString).collect(Collectors.joining())
        );
    }
}
