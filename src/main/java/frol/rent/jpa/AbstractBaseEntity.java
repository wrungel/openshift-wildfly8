package frol.rent.jpa;


import javax.persistence.*;
import java.nio.ByteBuffer;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractBaseEntity {

    @Id
    @Column(columnDefinition = "BINARY(16)", length = 16, updatable = false, nullable = false)
    private byte[] id;

    @Transient
    private String uuid;

    @PrePersist
    void prePersist() {
        if (this.id == null) {
            getId();
        }
    }

    public String getId() {
        if (this.id == null) {
            UUID u = UUID.randomUUID();
            ByteBuffer bb = ByteBuffer.allocate(16);

            bb.putLong(u.getMostSignificantBits()).putLong(u.getLeastSignificantBits());
            this.id = bb.array();
            this.uuid = u.toString();
        } else if (this.uuid == null) {
            ByteBuffer bb = ByteBuffer.wrap(this.id);
            UUID u = new UUID(bb.getLong(), bb.getLong());
            this.uuid = u.toString();
        }

        return this.uuid;
    }
}
