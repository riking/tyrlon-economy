package me.riking.tyrlon.datamodel;

public class PlayerAccount extends BasicAccount {
    protected String world = null;

    public PlayerAccount(String name) {
        super(name, 0);
    }

    public PlayerAccount(String name, double amount) {
        super(name, amount);
    }

    public PlayerAccount(String name, String world) {
        this(name, world, 0);
    }

    public PlayerAccount(String name, String world, double amount) {
        super(name, amount);
        this.world = world;
    }

    /**
     * Get the World this PlayerAccount is responsible for.
     * <p>
     * May be null.
     * 
     * @return world string
     */
    public String getWorld() {
        return world;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((world == null) ? 0 : world.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        PlayerAccount other = (PlayerAccount) obj;
        if (world == null) {
            if (other.world != null)
                return false;
        } else if (!world.equals(other.world))
            return false;
        return true;
    }
}
