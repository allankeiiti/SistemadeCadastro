package usuario.app.sistemadecadastro.View;
import android.app.*;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import usuario.app.sistemadecadastro.R;
import usuario.app.sistemadecadastro.Model.Registro;

public class TelaExcluirCadastro {
    MainActivity act;
    EditText edId;
    TextView txtenderecoDel, txttelefoneDel;
    Button btExcluir, btFechar, btPesquisar;
    TelaPrincipal tela_principal;
    public TelaExcluirCadastro(MainActivity act, TelaPrincipal tela_principal)
    {
        this.act = act;
        this.tela_principal = tela_principal;
    }
    public void CarregarTela()
    {
        if (act.getaRegistros().size() == 0){
            (new AlertDialog.Builder(act))
                    .setTitle("Aviso")
                    .setMessage("Não existe nenhum registro cadastrado.")
                    .setNeutralButton("OK", null)
                    .show();
            return;
        }else{
            act.setContentView(R.layout.excluir_usuario);
            btPesquisar = (Button) act.findViewById(R.id.btpesquisarDel);
            btExcluir = (Button) act.findViewById(R.id.btexcluir);
            btExcluir.setEnabled(false);
            btFechar = (Button) act.findViewById(R.id.btfechar);
            edId = (EditText) act.findViewById(R.id.edIdCadastro);
            txtenderecoDel = (TextView) act.findViewById(R.id.txtenderecoDel);
            txttelefoneDel = (TextView) act.findViewById(R.id.txttelefoneDel);

            btPesquisar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PesquisarRegistro(act.getaRegistros(), edId.getText().toString());
                }
            });

            btExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(act);
                    dialogo.setTitle("Aviso");
                    dialogo.setMessage("Tem certeza em excluir o usuário " + edId.getText() + " ?");
                    dialogo.setNegativeButton("Não", null);
                    dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ExcluirRegistro(act.getaRegistros(), edId.getText().toString());
                        }});
                    dialogo.show();
                }
            });

            btFechar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder dialogo = new
                            AlertDialog.Builder(act);
                    dialogo.setTitle("Aviso");
                    dialogo.setMessage("Sair da Exclusão ?");
                    dialogo.setNegativeButton("Não", null);
                    dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tela_principal.CarregarTela();
                        }
                    });
                    dialogo.show();
                }
            });
        }
    }
    public ArrayList<Registro> PesquisarRegistro(ArrayList<Registro> array, String nome){
        boolean found = true;
        for (int i = 0; i < array.size(); i++){
            array.get(i);
            if(array.get(i).getNome().equals(nome)){
                txtenderecoDel.setText(array.get(i).getEndereco());
                txttelefoneDel.setText(array.get(i).getTelefone());
                btExcluir.setEnabled(true);
                found = true;
                break;
            }else{
                found = false;
            }
        }
        if(found == false){
            act.ExibirMensagem(nome+" não localizado para a exclusão.");
        }
        return array;
    }

    // método retirado e editado do link abaixo:
    // https://stackoverflow.com/questions/20058810/remove-a-line-from-an-arrayliststudent-containing-a-specific-number
    public ArrayList<Registro> ExcluirRegistro(ArrayList<Registro> array, String nome){
        for (int i = 0; i < array.size(); i++){
            array.get(i);
            if(array.get(i).getNome().equals(nome)){
                array.remove(i);
                tela_principal.CarregarTela();
                act.ExibirMensagem(nome+" excluído.");
                break;
            }
        }
        return array;
    }
}
