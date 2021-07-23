/**
 * SRP - single responsibility principle
 * Принцип единственной ответственности
 * Программная сущность (класс, пакет, модуль) должна иметь только одну ответственность.
 *
 */

public interface XmlData<T> {

    void parseEx(String xml, String dateTimePattern, boolean replaceHtmlChars);

    String toXmlEx(String dateTimePattern, boolean replaceHtmlChars);

    // 1. Если цель интерфейса - создать мост между XML и каким-либо объектом,
    // то этот метод нарушает SRP, потому что не соответствует цели.
    void toStream(OutputStream out);

    // 2. Создание объекта типа T нарушает SRP
    // Уже есть независимый от обобщения метод parseEx
    T parseObject(String xml);

    // 3. Возможно нарушение SRP, потому что при реализации такого метода
    // класс должен будет самостоятельно определить как преобразовывать значения Date
    // и заменять ли спец. символы html в содержимом тегов.
    // В общем, эта сигнатура метода недостаточно ясно определяет цель интерфейса.
    String toXml();
}