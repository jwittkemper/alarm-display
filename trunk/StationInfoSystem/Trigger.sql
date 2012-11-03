delimiter $
DROP TRIGGER IF EXISTS RemoveUnused;

CREATE TRIGGER RemoveUnused AFTER INSERT ON monitord_pocsag

	FOR EACH ROW BEGIN
	   DECLARE foundKennung int;
	   SET foundKennung = ( Select count(*) FROM Kennungen Where RIC = NEW.kennung) ;
					
	   IF foundKennung <= 0 Then
	      DELETE FROM monitord_pocsag Where id = NEW.id;	
	   END IF;
	
	END;
    
$

delimiter ;
