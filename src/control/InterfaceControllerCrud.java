package control;

import control.*;
import java.util.ArrayList;

public interface InterfaceControllerCrud {

    int add(Object objeto);

    void remove(int id);

    void altera(Object objeto);

    Object get(int id);

    ArrayList getAll();

    ArrayList<String> valida(Object objeto);

}
