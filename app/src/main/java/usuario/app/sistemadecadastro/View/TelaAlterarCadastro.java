package usuario.app.sistemadecadastro.View;
import android.app.*;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.ArrayList;

import usuario.app.sistemadecadastro.Model.DatabaseHelper;
import usuario.app.sistemadecadastro.R;
import usuario.app.sistemadecadastro.Model.Registro;
public class TelaAlterarCadastro {
    MainActivity act;
    EditText ednome, edendereco, edtelefone;
    Button btalterar, btcancelar, btpesquisar;
    TelaPrincipal tela_principal;
    int index;
    public TelaAlterarCadastro(MainActivity act, TelaPrincipal tela_principal) {
        this.act = act;
        this.tela_principal = tela_principal;
    }
    public void CarregarTela() {

        // O IL ELSE abaixo verifica se há registros. Caso não tenha, a tela de exclusão não abrirá
        if (act.getaRegistros().size() == 0) {
            (new AlertDialog.Builder(act))
                    .setTitle("Aviso")
                    .setMessage("Não existe nenhum registro cadastrado.")
                    .setNeutralButton("OK", null)
                    .show();
            return;
        } else {
            act.setContentView(R.layout.alterar_usuario);
            ednome = (EditText) act.findViewById(R.id.ednome);
            edtelefone = (EditText) act.findViewById(R.id.edtelefone);
            edendereco = (EditText) act.findViewById(R.id.edendereco);
            btalterar = (Button) act.findViewById(R.id.btalterar);
            btpesquisar = (Button) act.findViewById(R.id.btpesquisar);
            btcancelar = (Button) act.findViewById(R.id.btcancelar);
            SimpleMaskFormatter SMTel = new SimpleMaskFormatter("(NN)NNNNN-NNNN");
            MaskTextWatcher maskTel = new MaskTextWatcher(edtelefone, SMTel);
            edtelefone.addTextChangedListener(maskTel);
            //Desabilitando os EditTexts de telefone e endereço antes da pesquisa ser validada:
            //https://stackoverflow.com/questions/5879250/how-to-disable-edittext-in-android
            edtelefone.setEnabled(false);
            edendereco.setEnabled(false);
            btalterar.setEnabled(false);
            btalterar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(act);
                    dialogo.setTitle("Aviso");
                    dialogo.setMessage("Alterar usuário ?");
                    dialogo.setNegativeButton("Não", null);
                    dialogo.setPositiveButton("Sim", new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    act.getaRegistros().set(index, new Registro(ednome.getText().toString(), edendereco.getText().toString(), edtelefone.getText().toString()));
                                    act.ExibirMensagem("Alteração efetuada com sucesso.");
                                    index = -1;
                                    tela_principal.CarregarTela();
                                }
                            });
                    dialogo.show();
                }
            });
            btpesquisar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PesquisarRegistro(act.getaRegistros(), ednome.getText().toString());
                    index = getIndex(act.getaRegistros(), ednome.getText().toString());
                }
            });
        }
        btcancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogo = new
                        AlertDialog.Builder(act);
                dialogo.setTitle("Aviso");
                dialogo.setMessage("Sair da alteração ?");
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
    // método retirado e editado do link abaixo:
    // https://stackoverflow.com/questions/20058810/remove-a-line-from-an-arrayliststudent-containing-a-specific-number
    public void PesquisarRegistro(ArrayList<Registro> array, String nome) {
        boolean found = true;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getNome().equals(nome)) {
                edendereco.setText(array.get(i).getEndereco());
                edtelefone.setText(array.get(i).getTelefone());
                edtelefone.setEnabled(true);
                edendereco.setEnabled(true);
                ednome.setEnabled(false);
                btalterar.setEnabled(true);
                btpesquisar.setEnabled(false);
                found = true;
                break;
            } else {
                edtelefone.setEnabled(false);
                edendereco.setEnabled(false);
                ednome.setEnabled(true);
                btalterar.setEnabled(false);
                btpesquisar.setEnabled(true);
                found = false;
            }
        }
        if(found == false){
            act.ExibirMensagem(nome + " não localizado para a alteração.");
        }
    }
    // método semelhante ao de cima, porém voltado para recuperar indice
    public int getIndex(ArrayList<Registro> array, String nome) {
        int x = -1;
        for (int i = 0; i < array.size(); i++) {
            array.get(i);
            if (array.get(i).getNome().equals(nome)) {
                x = i;
            }
        }
        return x;
    }
}
