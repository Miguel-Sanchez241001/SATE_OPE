CREATE OR REPLACE TRIGGER BNTG_19_EST_ASIGNACION
BEFORE INSERT ON BNSATE_19_EST_ASIGNACION
FOR EACH ROW
BEGIN
 
    IF :new.B019_EASI IS NULL THEN
    SELECT BNSQ_11_EST_ASIGNACION.nextval INTO :new.B019_EASI FROM DUAL;
  END IF;
END;
/