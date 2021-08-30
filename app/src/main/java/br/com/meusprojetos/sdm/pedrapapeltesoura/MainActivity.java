package br.com.meusprojetos.sdm.pedrapapeltesoura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;

import br.com.meusprojetos.sdm.pedrapapeltesoura.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        activityMainBinding.dedraIB.setOnClickListener(this);
        activityMainBinding.papelIB.setOnClickListener(this);
        activityMainBinding.tesouraIB.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(activityMainBinding.tresJogadores.isChecked()) {
            comTresJogadores(view);
        }else {
            comDoisJogadores(view);
        }
    }


    private void comTresJogadores(View view){
        Integer jogadaUsuario = defineJogadaUsuario(view);
        defineImagem(jogadaUsuario, activityMainBinding.suaJogadaIV, activityMainBinding.suaJogadaLL);
        System.out.println("valor Jogada usuario: " + jogadaUsuario);

        Integer jogadaMaquina01 = sorteiaJogadaMaquina();
        defineImagem(jogadaMaquina01, activityMainBinding.jogadaMaquina01IV, activityMainBinding.jogadaMaquina01LL);
        System.out.println("valor Jogada maquina01: " + jogadaMaquina01);

        Integer jogadaMaquina02 = sorteiaJogadaMaquina();
        defineImagem(jogadaMaquina02, activityMainBinding.jogadaMaquina02IV, activityMainBinding.jogadaMaquina02LL);
        System.out.println("valor Jogada maquina01: " + jogadaMaquina02);

        //verifica ganhador
        Integer resultado01 = verificaSeOprimeiroGanhoudoSegundo(jogadaMaquina01, jogadaMaquina02);
        Integer resultado02 = verificaSeOprimeiroGanhoudoSegundo(jogadaUsuario, jogadaMaquina01);
        Integer resultado03 = verificaSeOprimeiroGanhoudoSegundo(jogadaUsuario, jogadaMaquina02);

        if(resultado01 == 3 && resultado02 ==1){
            activityMainBinding.resultado.setText(R.string.voce_ganhou);
        }else if(resultado01 == 1 && resultado02 == 1){
            activityMainBinding.resultado.setText(R.string.voce_ganhou);
        }else if(resultado01 == 0 && resultado03 == 1){
            activityMainBinding.resultado.setText(R.string.voce_ganhou);
        }else if(resultado02 == 3 || resultado03 == 3){
            activityMainBinding.resultado.setText(R.string.empate);
        }else{
            activityMainBinding.resultado.setText(R.string.voce_perdeu);
        }
    }

    private void comDoisJogadores(View view){
        activityMainBinding.jogadaMaquina02LL.setVisibility(View.GONE);

        Integer jogadaUsuario = defineJogadaUsuario(view);
        defineImagem(jogadaUsuario, activityMainBinding.suaJogadaIV, activityMainBinding.suaJogadaLL);
        System.out.println("valor Jogada usuario: " + jogadaUsuario);

        Integer jogadaMaquina01 = sorteiaJogadaMaquina();
        defineImagem(jogadaMaquina01, activityMainBinding.jogadaMaquina01IV, activityMainBinding.jogadaMaquina01LL);
        System.out.println("valor Jogada maquina01: " + jogadaMaquina01);

        Integer resultado = verificaSeOprimeiroGanhoudoSegundo(jogadaUsuario, jogadaMaquina01);

        switch (resultado){
            case 0:
                activityMainBinding.resultado.setText(R.string.voce_perdeu);
                break;
            case 1:
                activityMainBinding.resultado.setText(R.string.voce_ganhou);
                break;
            case 3:
                activityMainBinding.resultado.setText(R.string.empate);
                break;
        }
    }

    private Integer defineJogadaUsuario(View view) {
        if (activityMainBinding.dedraIB.equals(view)) {
            return 0;
        } else if (activityMainBinding.papelIB.equals(view)) {
            return 1;
        }

        return 2;
    }

    private void defineImagem(Integer jogada, ImageView imagem, LinearLayout campo) {
        switch (jogada){
            case 0:
                imagem.setImageResource(R.mipmap.pedra);
                break;
            case 1:
                imagem.setImageResource(R.mipmap.papel);
                break;
            case 2:
                imagem.setImageResource(R.mipmap.tesoura);
                break;
            default:
                break;
        }
        campo.setVisibility(View.VISIBLE);
    }

    private Integer sorteiaJogadaMaquina(){
        Random gerador = new Random(System.currentTimeMillis());
        return gerador.nextInt(3);
    }

    private Integer verificaSeOprimeiroGanhoudoSegundo(Integer j1, Integer j2){
        //verifica se o primeito ganhou do segundo e retorna 1 se verdadeiro 0 se falso e 3 se empate
        if(j1 == 0 && j1!=j2){
            switch (j2){
                case 1:
                    return 0;
                case 2:
                    return 1;
            }
        }else if(j1 == 1 && j1!=j2){
            switch (j2){
                case 0:
                    return 1;
                case 2:
                    return 0;
            }
        }else if(j1 == 2 && j1!=j2){
            switch (j2){
                case 0:
                    return 0;
                case 1:
                    return 1;
            }
        }

        return 3;
    }

}