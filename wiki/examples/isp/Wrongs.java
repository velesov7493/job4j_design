/**
 * ISP - Interface segregation principle
 * Принцип разделения интерфейсов
 * Программные сущности не должны вынужденно зависеть от методов, которыми не пользуются.
 * Пересекается с SRP: Класс, реализующий интерфейс с нарушениями ISP, может нарушать и SRP
 */

// Интерфейс обработчивов событий
// Создавался для клиентского кода, изначально реализующего реакции на все эти события.
// Но в случае появления клиентов, которым нужна только часть событий,
// интерфейс понадобится разделить на части, содержащие только запрашиваемые методы.

public interface DeviceEvents {

    void onGpsLocatingPkg(SmartDevice sender, PduAP01 pkg);

    void onBasesLocatingPkg(SmartDevice sender, PduAP02 pkg);

    void onHeartbeatPkg(SmartDevice sender, PduAP03 pkg);

    void onAlarmLocatingPkg(SmartDevice sender, PduAP10 pkg);

    void onHeartRatePkg(SmartDevice sender, PduAP49 pkg);

    void onHeartRateAndBpPkg(SmartDevice sender, PduAPHT pkg);

    void onHrBpSaO2SugarPkg(SmartDevice sender, PduAPHP pkg);

    void onSosNumbersResp(SmartDevice sender, PduAP12 resp);

    void onWhiteListSetResp(SmartDevice sender, PduAP14 resp);

    void onWhereResp(SmartDevice sender, PduAP16 resp);

    void onResetResp(SmartDevice sender, PduAP17 resp);

    void onSendAudioResp(SmartDevice sender, PduAP28 resp);

    void onModeResp(SmartDevice sender, PduAP33 resp);

    void onWhiteListSwitchResp(SmartDevice sender, PduAP84 resp);

    void onAlarmsResp(SmartDevice sender, PduAP85 resp);

    void onAutoTestingTimerResp(SmartDevice sender, PduAP86 resp);

    void onCalibrateBloodPressureResp(SmartDevice sender, PduAPJZ resp);

    void onHeartRateResp(SmartDevice sender, PduAPXL resp);

    void onBloodPressureResp(SmartDevice sender, PduAPXY resp);

    void onUnknownPkg(SmartDevice sender, ProtocolDataUnit pkg);

    void onAudioAvailable(SmartDevice sender, AudioDataStore data);

    // Эта сигнатура метода нарушает ISP и SRP, потому что не соответствует цели интерфейса
    // А цель интерфейса - сообщить клиентскому коду сигнатуры обработчиков всех возможных событий системы
    Set<SmartDevice> getActiveDevices();
}