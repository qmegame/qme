package org.qme.utils;

/**
 * This file aims to provide a uniform interface for getting the OS type across
 * different files, which may use different and incomplete ways of getting the
 * operating system.
 * @author adamhutchings
 * @since 0.4
 */
public final class OSType {

    private OSType() { throw new IllegalStateException("OS Type"); }

    public static final int OS_TYPE = getOSType();

    public static final int
        WIN_XP  = 1,
        WIN_ME  = 2,
        WIN_NT  = 3,
        WIN_98  = 4,
        WIN_00  = 5,
        WIN_03  = 6,
        LINUX   = 7,
        BSD     = 8,
        SUNOS   = 9,
        MACOSX  = 10,
        INVALID = 11;
    ;

    public static boolean windows() { return (OS_TYPE <= WIN_XP) && (OS_TYPE >= WIN_03); }

    public static boolean mac() { return OS_TYPE == MACOSX; }

    public static boolean linux() { return OS_TYPE == LINUX; }

    public static boolean unix() { return !windows(); }

    public static boolean nonstandard() { return ! ( windows() || mac() || linux() ); }

    private static int getOSType() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("windows")) {
            if (osName.contains("xp"))   return WIN_XP;
            if (osName.contains("me"))   return WIN_ME;
            if (osName.contains("nt"))   return WIN_NT;
            if (osName.contains("1998")) return WIN_98;
            if (osName.contains("2000")) return WIN_00;
            if (osName.contains("1003")) return WIN_03;
        }
        if (osName.contains("linux"))    return LINUX;
        if (osName.contains("bsd"))      return BSD;
        if (osName.contains("sun"))      return SUNOS;
        if (osName.contains("mac"))      return MACOSX;
        return INVALID;
    }

}
