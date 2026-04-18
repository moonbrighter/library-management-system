module library.shared {
    requires java.rmi;
    requires com.google.gson;

    exports com.library.shared.rmi;
    exports com.library.shared.models;
    exports com.library.shared.util;
}