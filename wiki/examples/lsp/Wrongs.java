/*
* LSP - Liskov substitution principle
* Принцип подстановки Барбары Лисков.
* Наследникам работающего проверенного класса
* нельзя менять его код, можно только добавлять новый.
* Это частный случай OCP для иерархии классов.
* Нарушение этого правила приводит к возникновению новых ошибок при попытке подставить
* класса-потомка, содержащего нарушение, в рабочий клиентский код вместо самого класса.
*/

public class Parallelogram {

    protected double a;
    protected double b;
    protected double alfa;

    public Parallelogram(double a, double b, double alfa) {
        this.a = a;
        this.b = b;
        this.alfa = alfa;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getAlfa() {
        return alfa;
    }

    public void setAlfa(double alfa) {
        this.alfa = alfa;
    }
}

// Ромб - это параллелограмм, у которого все стороны равны
// Казалось бы, можно сделать ромб наследником параллелограмма,
// наложив такое ограничение, но это будет нарушением LSP

// 1-й способ ограничения

public class Rhombus extends Parallelogram {

    public Rhombus(double a, double alfa) {
        this.a = a;
        // 1. Ограничение на значения поля b класса-предка,
        this.b = a;
        this.alfa = alfa;
    }

    @Override
    public void setA(double a) {
        this.a = a;
        // 1. Ограничение на значения поля b класса-предка,
        this.b = a;
    }

    @Override
    public void setB(double b) {
        this.b = b;
        // 1. Ограничение на значения поля a класса-предка,
        this.a = b;
    }
}

// 2-й способ ограничения

public class Rhombus extends Parallelogram {

    public Rhombus(double a, double alfa) {
        this.a = a;
        this.alfa = alfa;
    }

    @Override
    public double getB() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setB(double b) {
        throw new UnsupportedOperationException();
    }
}