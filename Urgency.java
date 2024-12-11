
/**
 * Enumeration class Urgency - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Urgency {
        EMERGENCY("Emergency", 5),
        IMPORTANT("Important", 3),
        NONESSENTIAL("Non essential", 1);

        private final String name;
        private final int value;

        Urgency(String name, int value) {
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
            return "<urgency: " + getName() + "(value: " + getValor() + ")>";
        }
    }
