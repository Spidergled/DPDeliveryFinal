
/**
 * Enumeration class Surcharge - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Surcharge{
        MEDIUM("Medium", 10), LOW("Low", 4);
        private final String name;
        private final int value;

        Surcharge(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public int getValor() {
            return value;
        }
        
        @Override
        public String toString(){
            return "<charge: " + getName() + "(value: " + getValor() + ")>";
        }
    }
