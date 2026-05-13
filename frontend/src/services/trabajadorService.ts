import { type Trabajador } from "../types/Trabajador";

const API = "http://localhost:8080/trabajadores";

export const obtenerTrabajadores = async (): Promise<Trabajador[]> => {
  const response = await fetch(API);
  return await response.json();
};

export const crearTrabajador = async (trabajador: Trabajador) => {
  const response = await fetch(API, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(trabajador)
  });

  return await response.json();
};