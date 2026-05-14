import { useState } from "react";
import { login } from "../services/authService";

const Login = () => {

  const [usuario, setUsuario] = useState("");
  const [password, setPassword] = useState("");

  const iniciarSesion = async (
    e: React.FormEvent
  ) => {

    e.preventDefault();

    try {

      const data = await login(
        usuario,
        password
      );

      // GUARDAR TOKEN
      localStorage.setItem(
        "token",
        data.token
      );

      alert("Login exitoso");

      console.log(data);

    } catch (error) {

      console.error(error);

      alert("Credenciales incorrectas");
    }
  };

  return (

    <div>

      <h1>Iniciar Sesión</h1>

      <form onSubmit={iniciarSesion}>

        <div>
          <input
            type="text"
            placeholder="Usuario"
            value={usuario}
            onChange={(e) =>
              setUsuario(e.target.value)
            }
          />
        </div>

        <br />

        <div>
          <input
            type="password"
            placeholder="Contraseña"
            value={password}
            onChange={(e) =>
              setPassword(e.target.value)
            }
          />
        </div>

        <br />

        <button type="submit">
          Ingresar
        </button>

      </form>

    </div>
  );
};

export default Login;