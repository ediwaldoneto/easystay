package br.com.nt.easystay.domain.model;

public enum RoomType {

    SINGLE,
    DOUBLE,
    SUITE,
    FAMILY,
    DELUXE;

    public static RoomType fromString(String roomTypeName) {
        for (RoomType roomType : RoomType.values()) {
            if (roomType.name().equalsIgnoreCase(roomTypeName)) {
                return roomType;
            }
        }
        throw new IllegalArgumentException("No enum constant for value: " + roomTypeName);
    }
}
