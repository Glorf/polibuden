package aktywnosc;

/**
 * Interfejs gwarantuje obecność funkcji zabijającej proces - ważne ponieważ są dwa rodzaje procesów - Graczy i Spółek
 */
public interface KillableThread {
    public void kill();
}
