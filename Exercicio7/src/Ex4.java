import java.util.ArrayList;
import java.util.List;

interface IMeioTransporte {
    void acelerar();
    void frear();
    int getVelocidade();
}

class Carro implements IMeioTransporte {
    private int velocidade = 0;
    private final int MAX = 200;

    @Override
    public void acelerar() {
        if (velocidade + 20 > MAX) {
            throw new IllegalStateException("Velocidade máxima atingida!");
        }
        velocidade += 20;
    }

    @Override
    public void frear() {
        if (velocidade - 20 < 0) {
            throw new IllegalStateException("Carro já está parado!");
        }
        velocidade -= 20;
    }

    @Override
    public int getVelocidade() {
        return velocidade;
    }
}

class Bicicleta implements IMeioTransporte {
    private int velocidade = 0;
    private final int MAX = 40;

    @Override
    public void acelerar() {
        if (velocidade + 5 > MAX) {
            throw new IllegalStateException("Bicicleta atingiu o limite!");
        }
        velocidade += 5;
    }

    @Override
    public void frear() {
        if (velocidade - 5 < 0) {
            throw new IllegalStateException("Bicicleta já está parada!");
        }
        velocidade -= 5;
    }

    @Override
    public int getVelocidade() {
        return velocidade;
    }
}

class Trem implements IMeioTransporte {
    private int velocidade = 0;
    private final int MAX = 300;

    @Override
    public void acelerar() {
        if (velocidade + 50 > MAX) {
            throw new IllegalStateException("Trem não pode ultrapassar 300km/h!");
        }
        velocidade += 50;
    }

    @Override
    public void frear() {
        if (velocidade - 50 < 0) {
            throw new IllegalStateException("Trem já está parado!");
        }
        velocidade -= 50;
    }

    @Override
    public int getVelocidade() {
        return velocidade;
    }
}

public class Exercicio4 {
    public static void main(String[] args) {
        List<IMeioTransporte> meios = new ArrayList<>();
        meios.add(new Carro());
        meios.add(new Bicicleta());
        meios.add(new Trem());

        for (IMeioTransporte m : meios) {
            try {
                m.acelerar();
                m.acelerar();
                System.out.println(m.getClass().getSimpleName() +
                        " - Velocidade: " + m.getVelocidade());
                m.frear();
                System.out.println(m.getClass().getSimpleName() +
                        " após frear - Velocidade: " + m.getVelocidade());
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}
