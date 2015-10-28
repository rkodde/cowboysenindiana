package nl.cowboysenindiana.app.nl.cowboysenindiana.app.data;

/**
 * Basic class 'Child'
 * to manage children's details
 * Created by Sasha Antipin on 18-10-2015.
 */
public class Child {
        private int childNumber;
        private String childName;
        private String childNote;

        public Child(int childNumber, String childName, String childNote) {
            this.childNumber = childNumber;
            this.childName = childName;
            this.childNote = childNote;
        }

        public int getChildNumber() {
            return childNumber;
        }

        public String getChildName() { return childName; }

        public String getChildNote() { return childNote; }

        @Override
        public String toString() {
            return childName;
        }
}
