import { useEffect, useState } from "react";
import { obtenerTrabajadores } from "../services/trabajadorService";
import { type Trabajador } from "../types/Trabajador";

export default function TrabajadorList() {

  const [trabajadores, setTrabajadores] = useState<Trabajador[]>([]);

  useEffect(() => {
    cargarTrabajadores();
  }, []);

  const cargarTrabajadores = async () => {
    const data = await obtenerTrabajadores();
    setTrabajadores(data);
  };

  return (
    <div>
      <h1>Trabajadores</h1>

      <ul>
        {trabajadores.map((t) => (
          <li key={t.id}>
            {t.nombre} - {t.email}
          </li>
        ))}
      </ul>
    </div>
  );
}