import { AutomoveisComponentSyle } from "../styledConfig";
import { ListaAutomoveis } from "../types";

export default function Automovel({marca, placa} : ListaAutomoveis) {
    return (
        <AutomoveisComponentSyle>
            <h3>{marca}</h3>
            <h3>{placa}</h3>
        </AutomoveisComponentSyle>
    )
}