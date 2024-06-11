package com.scoks.order;

public interface Enums {
    enum Position {
        BOSS(1),
        SALESMAN(2),
        DIRECTOR(3),
        WORKER(4),
        FINALIZE(5),
        OUT(6),
        STORAGE(7);
        private int position;

        Position(int position) {
            this.position = position;
        }

        public int position() {
            return position;
        }

    }
}
