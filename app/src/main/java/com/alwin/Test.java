package com.alwin;

import androidx.annotation.NonNull;

public final class Test {

    public static void main() {
        T<G> temp = new T<>();
        System.out.println(G.i);
    }

    static class T<E> {
        static {
            System.out.println("TT");
        }
    }

    static class G {
        static {
            System.out.println("GG");
        }

        static int i = 1;

    }


}

