import { FormularioCadastroSectio } from "../styledConfig";
import { CriacaoFormulario } from "../types";

export default function Formulario({id, nomeCampo, tipo, placeHolder}: CriacaoFormulario) {
    return (
       <FormularioCadastroSectio>
            <label className="campo" htmlFor={id}>{nomeCampo}</label>
            <input className="input" type={tipo} id={id} placeholder={placeHolder}/>
       </FormularioCadastroSectio>
    )
}